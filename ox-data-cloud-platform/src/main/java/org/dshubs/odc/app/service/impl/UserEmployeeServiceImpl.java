package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.UserEmployeeService;
import org.dshubs.odc.infra.mapper.UserEmployeeMapper;
import org.dshubs.odc.domain.entity.UserEmployee;
import org.springframework.stereotype.Service;


/**
 * 用户员工逻辑控制层
 *
 * @author wangxian 2022-03-04
 */
@Service
public class UserEmployeeServiceImpl extends BaseServiceImpl<UserEmployeeMapper,UserEmployee> implements UserEmployeeService {

}

