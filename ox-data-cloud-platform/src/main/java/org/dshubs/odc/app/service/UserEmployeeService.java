package org.dshubs.odc.app.service;

import org.dshubs.odc.domain.entity.Employee;
import org.dshubs.odc.domain.entity.OauthUser;
import org.dshubs.odc.domain.entity.UserEmployee;
import org.dshubs.odc.mybatis.app.service.IBaseService;


/**
 * 用户员工逻辑控制层
 *
 * @author wangxian 2022-03-04
 */
public interface UserEmployeeService extends IBaseService<UserEmployee> {

    /**
     * 将员工转成成系统用户
     *
     * @param employeeId 员工ID
     * @return 用户
     */
    OauthUser employeeConvertToUser(Long employeeId);

    /**
     * 将用户转换成员工
     *
     * @param userId 用户ID
     * @return 员工
     */
    Employee userConvertToEmployee(Long userId);
}

