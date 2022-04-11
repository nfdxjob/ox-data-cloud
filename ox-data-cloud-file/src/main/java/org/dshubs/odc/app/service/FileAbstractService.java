package org.dshubs.odc.app.service;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.domain.entity.FileResource;
import org.dshubs.odc.domain.entity.FileStorageConfig;
import org.dshubs.odc.vo.FileInfoVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Mr.zhou 2022/4/8
 **/
public abstract class FileAbstractService {

    protected FileStorageConfig fileStorageConfig;

    /**
     * 文件上传
     * @param file 文件
     * @param bucket 桶名称
     * @param directory 目录
     * @param fileName 文件名
     * @return fileKey
     * @throws Exception 异常
     */
    public abstract FileInfoVO upload(MultipartFile file, String bucket, String directory, String fileName) throws Exception;

    /**
     * PostPolicy 策略
     * @param bucket 桶名称
     * @return policy
     */
    public abstract Map<String, String> postPolicy(String bucket);

    /**
     * 文件下载
     * @param bucket 桶名称
     * @param fileKey fileKey
     * @param fileName 文件名
     * @param response 响应
     */
    public abstract void download(String bucket, String fileKey, String fileName, HttpServletResponse response);

    /**
     * 文件更改
     *
     * @param fileResource 原来的文件信息
     * @param file 文件
     * @param bucketName 桶名称
     * @param directory 目录
     * @return 文件信息
     */
    public abstract FileInfoVO update(FileResource fileResource, MultipartFile file, String bucketName, String directory);

    /**
     * 根据fileKey删除文件
     * @param bucketName 桶名称
     * @param fileKey fileKey
     * @throws Exception 异常
     */
    public abstract void delete(String bucketName, String fileKey) throws Exception;

    public void initStorageConfig(FileStorageConfig fileStorageConfig) {
        this.fileStorageConfig = fileStorageConfig;
    }

    protected String realBucket(String bucket) {
        return StringUtils.isNotBlank(fileStorageConfig.getBucketPrefix()) ? String.format("%s-%s", fileStorageConfig.getBucketPrefix(), bucket) : bucket;
    }

    protected String realPreUrl(String bucket) {
        String realBucket = realBucket(bucket);
        return String.format("%s/%s", StringUtils.isNotBlank(fileStorageConfig.getDomain()) ? fileStorageConfig.getDomain() : fileStorageConfig.getEndPoint(), realBucket);
    }

    protected void buildResponse(InputStream inputStream, HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-disposition","attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        IOUtils.copy(inputStream, response.getOutputStream());
    }

}
