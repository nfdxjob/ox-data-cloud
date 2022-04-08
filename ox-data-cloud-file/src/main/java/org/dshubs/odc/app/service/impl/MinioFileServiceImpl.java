package org.dshubs.odc.app.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import io.minio.*;
import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.app.service.FileAbstractService;
import org.dshubs.odc.app.service.FileResourceService;
import org.dshubs.odc.domain.entity.FileResource;
import org.dshubs.odc.vo.FileInfoVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @author Mr.zhou 2022/4/7
 **/
@Service
public class MinioFileServiceImpl extends FileAbstractService {

    private final MinioClient minioClient;
    private final FileResourceService fileResourceService;

    public MinioFileServiceImpl(MinioClient minioClient, FileResourceService fileResourceService) {
        this.minioClient = minioClient;
        this.fileResourceService = fileResourceService;
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

        fileResourceService.insert(fileResource);

        return new FileInfoVO().setFileKey(fileKey).setFileName(fileResource.getFileName());
    }

    @Override
    public void download(String bucket, String fileKey, String fileName, HttpServletResponse response) throws Exception {
        try (InputStream is = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucket)
                        .object(fileKey)
                        .build())) {
            buildResponse(is, response, fileName);
        }
    }

    @Override
    public void delete(String bucketName, String fileKey) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(fileKey).build());
    }

    private void makeBucket(String bucket) throws Exception {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }

}
