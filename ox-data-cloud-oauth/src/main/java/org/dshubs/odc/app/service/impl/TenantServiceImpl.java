package org.dshubs.odc.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.TenantService;
import org.dshubs.odc.domain.entity.Tenant;
import org.dshubs.odc.infra.mapper.TenantMapper;
import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author create by wangxian 2022/2/19
 */
@Service
@Slf4j
public class TenantServiceImpl extends BaseServiceImpl<TenantMapper, Tenant> implements TenantService {
}
