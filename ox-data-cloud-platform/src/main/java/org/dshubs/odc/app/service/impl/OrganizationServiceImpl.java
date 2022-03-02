package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.OrganizationService;
import org.dshubs.odc.infra.mapper.OrganizationMapper;
import org.dshubs.odc.domain.entity.Organization;
import org.springframework.stereotype.Service;


/**
 * 组织部门逻辑控制层
 *
 * @author wangxian 2022-03-02
 */
@Service
public class OrganizationServiceImpl extends BaseServiceImpl<OrganizationMapper,Organization> implements OrganizationService {

}

