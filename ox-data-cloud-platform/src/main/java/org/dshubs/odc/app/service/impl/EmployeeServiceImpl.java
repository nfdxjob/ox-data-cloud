package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.api.vo.EmployeeListQueryVO;
import org.dshubs.odc.app.service.EmployeeService;
import org.dshubs.odc.domain.entity.Employee;
import org.dshubs.odc.infra.mapper.EmployeeMapper;
import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.springframework.stereotype.Service;


/**
 * 员工信息逻辑控制层
 *
 * @author wangxian 2022-03-02
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Override
    public PageData<Employee> listEmployee(PageRequest page, EmployeeListQueryVO param) {
        return this.getPageData(this.baseMapper.listEmployee(getPageQuery(page), param));
    }
}

