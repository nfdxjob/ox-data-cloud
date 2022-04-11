package org.dshubs.odc.app.service;

import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.app.service.impl.MinioFileServiceImpl;
import org.dshubs.odc.app.service.impl.OssFileServiceImpl;
import org.dshubs.odc.constant.enums.StorageType;
import org.dshubs.odc.core.exception.CommonException;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.FileStorageConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Mr.zhou 2022/4/8
 **/
@Service
public class FileServiceFactory {

    private final FileStorageConfigService fileStorageConfigService;
    private final MinioFileServiceImpl minioFileService;
    private final OssFileServiceImpl ossFileService;

    public FileServiceFactory(FileStorageConfigService fileStorageConfigService, MinioFileServiceImpl minioFileService, OssFileServiceImpl ossFileService) {
        this.fileStorageConfigService = fileStorageConfigService;
        this.minioFileService = minioFileService;
        this.ossFileService = ossFileService;
    }

    public FileAbstractService build (String storageCode) {

        // 查询储存配置
        FileStorageConfig fileStoreByType;
        if (StringUtils.isBlank(storageCode)) {
            fileStoreByType = fileStorageConfigService.getDefaultFileStore();
            storageCode = fileStoreByType.getStorageType();
        } else {
            fileStoreByType = fileStorageConfigService.getFileStoreByType(storageCode);
        }

        if (StringUtils.isBlank(storageCode)) {
            throw new CommonException(new Results.ErrorResult("500", "没有文件上传配置"));
        }

        FileAbstractService fileAbstractService;
        StorageType storyType = StorageType.getStoryType(storageCode);
        Assert.notNull(storyType, "没有文件上传配置");
        switch (storyType) {
            case MINIO:
                fileAbstractService = minioFileService;
                break;
            case ALIYUN:
                fileAbstractService = ossFileService;
                break;
            default:
                fileAbstractService = null;
        }

        Assert.notNull(fileAbstractService, "没有文件上传配置");
        fileAbstractService.initStorageConfig(fileStoreByType);
        return fileAbstractService;
    }

}
