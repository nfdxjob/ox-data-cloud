package org.dshubs.odc.config;

import io.minio.MinioClient;
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
    public MinioClient minioClient(FileStorageConfigService fileStorageConfigService)  {
        FileStorageConfig fileStore = fileStorageConfigService.getFileStoreByType(StorageType.MINIO.getType());
//        return new MinioClient(fileStore.getEndPoint(), 9000, fileStore.getAccessKey(), fileStore.getAccessKeySecret());
        return MinioClient.builder()
                .endpoint(fileStore.getEndPoint(), 9000, false)
                .credentials(fileStore.getAccessKey(), fileStore.getAccessKeySecret())
                .build();
    }
}
