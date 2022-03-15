package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.app.service.EmployeeService;
import org.dshubs.odc.app.service.OauthUserService;
import org.dshubs.odc.app.service.UserEmployeeService;
import org.dshubs.odc.core.exception.CommonException;
import org.dshubs.odc.core.properties.OdcCoreProperties;
import org.dshubs.odc.domain.entity.Employee;
import org.dshubs.odc.domain.entity.OauthUser;
import org.dshubs.odc.domain.entity.UserEmployee;
import org.dshubs.odc.infra.exception.PlatformErrorEnum;
import org.dshubs.odc.infra.mapper.UserEmployeeMapper;
import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 用户员工逻辑控制层
 *
 * @author wangxian 2022-03-04
 */
@Service
public class UserEmployeeServiceImpl extends BaseServiceImpl<UserEmployeeMapper, UserEmployee> implements UserEmployeeService {

    private final OauthUserService oauthUserService;

    private final EmployeeService employeeService;

    private final OdcCoreProperties coreProperties;

    private final PasswordEncoder passwordEncoder;


    public UserEmployeeServiceImpl(OauthUserService oauthUserService, EmployeeService employeeService, OdcCoreProperties coreProperties, PasswordEncoder passwordEncoder) {
        this.oauthUserService = oauthUserService;
        this.employeeService = employeeService;
        this.coreProperties = coreProperties;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public OauthUser employeeConvertToUser(Long employeeId) {
        UserEmployee userEmployee = this.baseMapper.selectByEmployeeId(employeeId);
        if (userEmployee != null) {
            throw new CommonException(PlatformErrorEnum.EMPLOYEE_BOUND_USER);
        }
        Employee employee = employeeService.selectById(employeeId);
        if (employee != null) {
            OauthUser oauthUser = new OauthUser();
            oauthUser.setUsername(employee.getDomainAccount());
            oauthUser.setEmail(employee.getEmail());
            oauthUser.setRealName(employee.getEmployeeName());
            oauthUser.setPhone(employee.getPhone());
            oauthUser.setPassword(passwordEncoder.encode(coreProperties.getPlatform().getUserDefaultPassword()));
            oauthUser.setTenantId(coreProperties.getPlatform().getUserDefaultTenantId());

            oauthUserService.insert(oauthUser);
            userEmployee = new UserEmployee();
            userEmployee.setEmployeeId(employeeId);
            userEmployee.setUserId(oauthUser.getUserId());
            userEmployee.setUsername(employee.getEmployeeNum());
            userEmployee.setEmployeeNum(employee.getEmployeeNum());
            this.baseMapper.insert(userEmployee);
            return oauthUser;
        }
        throw new CommonException(PlatformErrorEnum.EMPLOYEE_NOT_EXISTS);
    }

    @Override
    public Employee userConvertToEmployee(Long userId) {
        return null;
    }
}

