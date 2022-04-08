package org.dshubs.odc.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dshubs.odc.constant.CacheConstant;
import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.FileStorageConfigService;
import org.dshubs.odc.infra.mapper.FileStorageConfigMapper;
import org.dshubs.odc.domain.entity.FileStorageConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * 文件存储配置逻辑控制层
 *
 * @author zhou 2022-04-06
 */
@Service
public class FileStorageConfigServiceImpl extends BaseServiceImpl<FileStorageConfigMapper,FileStorageConfig> implements FileStorageConfigService {

    @Override
    @Cacheable(value = CacheConstant.FILE_STORAGE_CONFIG, key = "default")
    public FileStorageConfig getDefaultFileStore() {
        return this.baseMapper.selectDefaultFileStore();
    }

    @Override
    @Cacheable(value = CacheConstant.FILE_STORAGE_CONFIG, key = "#storageType")
    public FileStorageConfig getFileStoreByType(String storageType) {
        return this.baseMapper.selectOne(new QueryWrapper<FileStorageConfig>().eq("storage_type", storageType));
    }

}

