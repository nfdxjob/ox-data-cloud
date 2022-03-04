package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.app.service.CustomDataPermissionService;
import org.dshubs.odc.domain.entity.CustomDataPermission;
import org.dshubs.odc.infra.mapper.CustomDataPermissionMapper;
import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 数据权限逻辑控制层
 *
 * @author wangxian 2022-03-04
 */
@Service
public class CustomDataPermissionServiceImpl extends BaseServiceImpl<CustomDataPermissionMapper, CustomDataPermission> implements CustomDataPermissionService {

}


