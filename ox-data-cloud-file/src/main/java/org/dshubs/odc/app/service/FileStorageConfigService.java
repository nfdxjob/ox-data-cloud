package org.dshubs.odc.app.service;

import org.dshubs.odc.domain.entity.FileStorageConfig;
import org.dshubs.odc.mybatis.app.service.IBaseService;


/**
 * 文件存储配置逻辑控制层
 *
 * @author zhou 2022-04-06
 */
public interface FileStorageConfigService extends IBaseService<FileStorageConfig>  {

    /**
     * 获取文件储存默认配置
     *
     * @return 文件配置
     */
    FileStorageConfig getDefaultFileStore();

    /**
     * 根据储存类型获取储存配置
     *
     * @param storageType 储存类型学
     * @return 文件配置
     */
    FileStorageConfig getFileStoreByType(String storageType);

    String hello();

}

