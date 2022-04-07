package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dshubs.odc.domain.entity.OauthRole;


/**
* 角色数据访问层
*
* @author wangxian 2022-03-04
*/
@Mapper
public interface OauthRoleMapper extends BaseMapper<OauthRole> {

}
