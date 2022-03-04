package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dshubs.odc.domain.entity.CustomDataPermission;
import org.apache.ibatis.annotations.Mapper;


/**
* 数据权限数据访问层
*
* @author wangxian 2022-03-04
*/
@Mapper
public interface CustomDataPermissionMapper extends BaseMapper<CustomDataPermission> {

}
