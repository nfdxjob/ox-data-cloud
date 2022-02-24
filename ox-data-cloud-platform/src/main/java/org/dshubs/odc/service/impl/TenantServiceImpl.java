package org.dshubs.odc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.domain.entity.Tenant;
import org.dshubs.odc.infra.mapper.TenantMapper;
import org.dshubs.odc.service.TenantService;
import org.springframework.stereotype.Service;

/**
 * @author create by wangxian 2022/2/19
 */
@Service
@Slf4j
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {
}
