package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dshubs.odc.domain.entity.FileEditLog;
import org.apache.ibatis.annotations.Mapper;


/**
* 文件更改版本表数据访问层
*
* @author Mr.zhou 2022-04-11
*/
@Mapper
public interface FileEditLogMapper extends BaseMapper<FileEditLog> {

}
