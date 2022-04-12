package org.dshubs.odc.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.dshubs.odc.app.service.FileStorageConfigService;
import org.dshubs.odc.constant.enums.StorageType;
import org.dshubs.odc.domain.entity.FileStorageConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.zhou 2022/4/12
 **/
@Configuration
public class OssConfig {

    @Bean
    public OSS ossClient(FileStorageConfigService fileStorageConfigService) {
        FileStorageConfig fileStore = fileStorageConfigService.getFileStoreByType(StorageType.ALIYUN.getType());
        return new OSSClientBuilder().build(fileStore.getEndPoint(), fileStore.getAccessKey(), fileStore.getAccessKeySecret());
    }
}
