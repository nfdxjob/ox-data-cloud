package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.OauthUserRoleService;
import org.dshubs.odc.infra.mapper.OauthUserRoleMapper;
import org.dshubs.odc.domain.entity.OauthUserRole;
import org.springframework.stereotype.Service;


/**
 * 用户角色逻辑控制层
 *
 * @author wangxian 2022-03-04
 */
@Service
public class OauthUserRoleServiceImpl extends BaseServiceImpl<OauthUserRoleMapper,OauthUserRole> implements OauthUserRoleService {

}

