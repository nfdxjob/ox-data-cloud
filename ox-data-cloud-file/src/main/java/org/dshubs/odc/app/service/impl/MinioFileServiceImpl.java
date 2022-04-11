package org.dshubs.odc.app.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import io.minio.*;
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
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Mr.zhou 2022/4/7
 **/
@Service
@Slf4j
public class MinioFileServiceImpl extends FileAbstractService {

    public static final String DEFAULT_BUCKET = "default";

    private final MinioClient minioClient;
    private final FileResourceService fileResourceService;
    private final FileEditLogService fileEditLogService;

    public MinioFileServiceImpl(MinioClient minioClient, FileResourceService fileResourceService, FileEditLogService fileEditLogService) {
        this.minioClient = minioClient;
        this.fileResourceService = fileResourceService;
        this.fileEditLogService = fileEditLogService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileInfoVO upload(MultipartFile file, String bucket, String directory, String fileName) throws Exception {

        String realBucket = realBucket(bucket);
        this.makeBucket(realBucket);
        String fileNamePre = UuidUtils.generateUuid().replaceAll("-", "") + "@";
        String newFileName = StringUtils.isNotBlank(fileName) ? fileName : file.getOriginalFilename();
        String uploadFileName = fileNamePre + newFileName;
        String fileKey = StringUtils.isBlank(directory) ? uploadFileName : String.format("%s/%s", directory, uploadFileName);
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(realBucket)
                .object(fileKey)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build());

        String url = String.format("%s/%s", realPreUrl(bucket), fileKey);
        FileResource fileResource = new FileResource()
                .setFileUrl(url)
                .setFileKey(fileKey)
                .setFileDirectory(directory)
                .setFileType(file.getContentType())
                .setFileName(newFileName)
                .setFileSize(file.getSize())
                .setBucketName(bucket)
                .setFileMd5(DigestUtils.md5DigestAsHex(file.getInputStream()))
                .setStorageCode(super.fileStorageConfig.getStorageType());


        FileResource insertFileResource = fileResourceService.insert(fileResource);
        FileEditLog fileEditLog = new FileEditLog();
        BeanUtils.copyProperties(insertFileResource, fileEditLog);
        String version = UuidUtils.generateUuid().replaceAll("-", "");
        fileEditLog.setFileVersion(version);
        fileEditLogService.insert(fileEditLog);

        return new FileInfoVO().setFileResourceId(insertFileResource.getFileResourceId()).setFileKey(fileKey).setFileName(fileResource.getFileName()).setFileVersion(version);
    }

    @Override
    public Map<String, String> postPolicy(String bucket) {

        String bucketName = StringUtils.isBlank(bucket) ? DEFAULT_BUCKET : bucket;
        String realBucket = realBucket(bucketName);
        this.makeBucket(realBucket);
        // 创建policy策略，10s过期时间
        PostPolicy policy = new PostPolicy(realBucket, ZonedDateTime.now().plusSeconds(10));
        // 文件对象要有此前缀
        policy.addStartsWithCondition("key", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        // 'content-length' 大小在 64kiB 到 10MiB之间
//        policy.addContentLengthRangeCondition(64 * 1024, 10 * 1024 * 1024);
        try {
            Map<String, String> postFormData = minioClient.getPresignedPostFormData(policy);
            postFormData.put("endpoint", realPreUrl(bucketName));
            return postFormData;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    @Override
    public void download(String bucket, String fileKey, String fileName, HttpServletResponse response) {
        try (InputStream is = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucket)
                        .object(fileKey)
                        .build())) {
            buildResponse(is, response, fileName);
        } catch (Exception e) {
            log.error("文件下载错误，fileKey -> {}", fileKey);
            log.error(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileInfoVO update(FileResource fileResource, MultipartFile file, String bucketName, String directory) {
        String realBucket = realBucket(bucketName);
        this.makeBucket(realBucket);
        String fileNamePre = UuidUtils.generateUuid().replaceAll("-", "") + "@";
        String fileName = file.getOriginalFilename();
        String uploadFileName = fileNamePre + fileName;
        String fileKey = StringUtils.isBlank(directory) ? uploadFileName : String.format("%s/%s", directory, uploadFileName);
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(realBucket)
                    .object(fileKey)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());

            // 更新
            fileResource.setFileKey(fileKey);
            fileResourceService.update(fileResource);
            // 增加一个文件版本
            FileEditLog fileEditLog = new FileEditLog();
            BeanUtils.copyProperties(fileResource, fileEditLog);
            fileEditLog.setFileVersion(UuidUtils.generateUuid().replaceAll("-", ""));
            fileEditLogService.insert(fileEditLog);

            return new FileInfoVO().setFileResourceId(fileResource.getFileResourceId()).setFileName(fileResource.getFileName()).setFileKey(fileResource.getFileKey()).setFileVersion(fileEditLog.getFileVersion());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CommonException(new Results.ErrorResult("500", "更新文件失败"));
        }
    }

    @Override
    public void delete(String bucketName, String fileKey) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(fileKey).build());
    }

    private void makeBucket(String bucket) {
        try {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("创建bucket失败");
            throw new CommonException(new Results.ErrorResult("500", "创建bucket失败：" + bucket));
        }

    }

}
