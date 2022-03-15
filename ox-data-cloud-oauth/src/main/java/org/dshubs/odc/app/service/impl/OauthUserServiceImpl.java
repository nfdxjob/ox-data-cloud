package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.app.service.OauthUserService;
import org.dshubs.odc.domain.entity.OauthUser;
import org.dshubs.odc.infra.mapper.OauthUserMapper;
import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 用户信息逻辑控制层
 *
 * @author wangxian 2022-03-04
 */
@Service
public class OauthUserServiceImpl extends BaseServiceImpl<OauthUserMapper,OauthUser> implements OauthUserService {

}

