package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dshubs.odc.domain.entity.FileStorageConfig;
import org.apache.ibatis.annotations.Mapper;


/**
* 文件存储配置数据访问层
*
* @author zhou 2022-04-06
*/
@Mapper
public interface FileStorageConfigMapper extends BaseMapper<FileStorageConfig> {

    FileStorageConfig selectDefaultFileStore();
}
