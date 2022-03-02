package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dshubs.odc.domain.entity.Organization;
import org.apache.ibatis.annotations.Mapper;


/**
* 组织部门数据访问层
*
* @author wangxian 2022-03-02
*/
@Mapper
public interface OrganizationMapper extends BaseMapper<Organization> {

}
