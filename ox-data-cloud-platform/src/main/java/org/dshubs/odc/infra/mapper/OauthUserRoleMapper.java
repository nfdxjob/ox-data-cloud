package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dshubs.odc.domain.entity.OauthUserRole;
import org.apache.ibatis.annotations.Mapper;


/**
* 用户角色数据访问层
*
* @author wangxian 2022-03-04
*/
@Mapper
public interface OauthUserRoleMapper extends BaseMapper<OauthUserRole> {

}
