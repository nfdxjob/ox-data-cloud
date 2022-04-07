package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dshubs.odc.domain.entity.FileResource;
import org.apache.ibatis.annotations.Mapper;


/**
* 文件资源数据访问层
*
* @author zhou 2022-04-06
*/
@Mapper
public interface FileResourceMapper extends BaseMapper<FileResource> {

}
