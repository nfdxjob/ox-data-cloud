package org.dshubs.odc.app.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import io.minio.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.app.service.FileResourceService;
import org.dshubs.odc.app.service.FileService;
import org.dshubs.odc.core.exception.CommonException;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.FileResource;
import org.dshubs.odc.dto.DownloadDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @author Mr.zhou 2022/4/7
 **/
@Service
public class MinioFileServiceImpl implements FileService {

    private final MinioClient minioClient;
    private final FileResourceService fileResourceService;

    public MinioFileServiceImpl(MinioClient minioClient, FileResourceService fileResourceService) {
        this.minioClient = minioClient;
        this.fileResourceService = fileResourceService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String upload(MultipartFile file, String bucket, String fileName) throws Exception {
        this.makeBucket(bucket);
        String fileNamePre = UuidUtils.generateUuid().replaceAll("-", "") + "@";
        String newFileName = fileNamePre + (StringUtils.isNotBlank(fileName) ? fileName : file.getOriginalFilename());
        minioClient.putObject(PutObjectArgs.builder().bucket(bucket)
                .object(newFileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build());

        String fileKey = bucket + "/" + newFileName;
        FileResource fileResource = new FileResource();
        fileResource.setFileUrl(fileKey);
        fileResource.setFileKey(fileKey);
        fileResource.setFileDirectory(newFileName);
        fileResource.setFileType(file.getContentType());
        fileResource.setFileName(file.getOriginalFilename());
        fileResource.setFileSize(file.getSize());
        fileResource.setBucketName(bucket);

        fileResourceService.insert(fileResource);

        return fileKey;
    }

    @Override
    public void download(DownloadDTO downloadDTO, HttpServletResponse response) throws Exception {
        FileResource fileResource = fileResourceService.queryByFileKey(downloadDTO.getFileKey());
        if (fileResource == null) {
            throw new CommonException(new Results.ErrorResult("500", "无效fileKey"));
        }
        try (InputStream is = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(fileResource.getBucketName())
                        .object(fileResource.getFileDirectory())
                        .build())) {
            IOUtils.copy(is, response.getOutputStream());
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileResource.getFileName(), "UTF-8"));
        }
    }

    private void makeBucket(String bucket) throws Exception {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }

}
