package org.dshubs.odc.app.service;

import org.dshubs.odc.api.vo.EmployeeListQueryVO;
import org.dshubs.odc.domain.entity.Employee;
import org.dshubs.odc.mybatis.app.service.IBaseService;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;


/**
 * 员工信息逻辑控制层
 *
 * @author wangxian 2022-03-02
 */
public interface EmployeeService extends IBaseService<Employee> {

    /**
     * List employee
     *
     * @param page  page
     * @param param search condition
     * @return Page
     */
    PageData<Employee> listEmployee(PageRequest page, EmployeeListQueryVO param);

}

