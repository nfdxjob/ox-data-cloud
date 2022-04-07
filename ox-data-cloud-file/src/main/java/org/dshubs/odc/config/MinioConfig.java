package org.dshubs.odc.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.dshubs.odc.app.service.FileStorageConfigService;
import org.dshubs.odc.constant.enums.StorageType;
import org.dshubs.odc.domain.entity.FileStorageConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.zhou 2022/4/6
 **/
@Configuration
public class MinioConfig {

    @Bean
    public MinioClient minioClient(FileStorageConfigService fileStorageConfigService) throws InvalidPortException, InvalidEndpointException {
        FileStorageConfig fileStore = fileStorageConfigService.getFileStoreByType(StorageType.MINIO.getType());
        return new MinioClient(fileStore.getEndPoint(), fileStore.getAccessKey(), fileStore.getAccessKeySecret());
    }
}