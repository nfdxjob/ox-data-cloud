package org.dshubs.odc.app.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.app.service.FileAbstractService;
import org.dshubs.odc.app.service.FileEditLogService;
import org.dshubs.odc.app.service.FileResourceService;
import org.dshubs.odc.core.exception.CommonException;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.FileEditLog;
import org.dshubs.odc.domain.entity.FileResource;
import org.dshubs.odc.vo.FileInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mr.zhou 2022/4/8
 **/
@Service
@Slf4j
public class OssFileServiceImpl extends FileAbstractService {

    private static final long EXPIRE_END_TIME = 30 * 1000;
    private static final long CONTENT_LENGTH_RANGE = 5L * 1024 * 1024 * 1024;
    private final OSS oss;
    private final FileResourceService fileResourceService;
    private final FileEditLogService fileEditLogService;

    public OssFileServiceImpl(OSS oss, FileResourceService fileResourceService, FileEditLogService fileEditLogService) {
        this.oss = oss;
        this.fileResourceService = fileResourceService;
        this.fileEditLogService = fileEditLogService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String upload(MultipartFile file, String bucketName, String objectName) {

        try {
            this.makeBucket(bucketName);
            PutObjectResult result = oss.putObject(new PutObjectRequest(bucketName, objectName, new BufferedInputStream(file.getInputStream())));
            return result.getVersionId();
        } catch (Exception e) {
            log.error("OSS上传文件错误");
            log.error(e.getMessage(), e);
            throw new CommonException(new Results.ErrorResult("500", "OSS上传文件错误"));
        }
    }

    @Override
    public Map<String, String> postPolicy(String bucket) {
        try {
            String realPreUrl = getRealPreUrl(StringUtils.isBlank(bucket) ? DEFAULT_BUCKET : bucket);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policy = new PolicyConditions();
            policy.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, CONTENT_LENGTH_RANGE);
            // 文件目录前缀必须是当天日期
            String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            policy.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            // 默认30s过期
            String postPolicy = oss.generatePostPolicy(new Date(System.currentTimeMillis() + EXPIRE_END_TIME), policy);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = oss.calculatePostSignature(postPolicy);

            Map<String, String> respMap = new LinkedHashMap<>();
            respMap.put("accessid", super.fileStorageConfig.getAccessKey());
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", realPreUrl);
            respMap.put("expire", String.valueOf(EXPIRE_END_TIME / 1000));

            return respMap;
        } catch (Exception e) {
            log.error("OSS创建policy策略失败");
            log.error(e.getMessage(), e);
        } finally {
            oss.shutdown();
        }

        return null;
    }

    @Override
    public void download(String bucket, String fileKey, String fileName, HttpServletResponse response) {
        try (InputStream is = new BufferedInputStream(oss.getObject(bucket, fileKey).getObjectContent())) {
            buildDownloadResponse(is, response, fileName);
        } catch (Exception e) {
            log.error("OSS下载错误");
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public FileInfoVO update(FileResource fileResource, MultipartFile file, String bucketName, String directory) {
        return null;
    }

    @Override
    public void delete(String bucketName, String fileKey) {
        try {
            oss.deleteObject(bucketName, fileKey);
        } catch (Exception e) {
            log.error("OSS删除文件失败");
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public String getRealPreUrl(String bucket) {
        String realBucket = getRealBucket(bucket);
        return String.format("%s%s.%s", "https://", realBucket, fileStorageConfig.getEndPoint());
    }

    private void makeBucket(String bucket) {
        try {
            if (!oss.doesBucketExist(bucket)) {
                oss.createBucket(new CreateBucketRequest(bucket));
            }
        } catch (Exception e) {
            log.error("OSS创建储存桶失败");
            log.error(e.getMessage(), e);
        }
    }

}
