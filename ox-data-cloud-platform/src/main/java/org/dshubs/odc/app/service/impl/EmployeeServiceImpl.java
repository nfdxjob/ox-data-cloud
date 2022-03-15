package org.dshubs.odc.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.api.vo.EmployeeListQueryVO;
import org.dshubs.odc.app.service.EmployeeService;
import org.dshubs.odc.domain.entity.Employee;
import org.dshubs.odc.infra.mapper.EmployeeMapper;
import org.dshubs.odc.infra.util.EmployeeDomainAccountUtils;
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
@Slf4j
public class EmployeeServiceImpl extends BaseServiceImpl<EmployeeMapper, Employee> implements EmployeeService {


    @Override
    public PageData<Employee> listEmployee(PageRequest page, EmployeeListQueryVO param) {
        return this.getPageData(this.baseMapper.listEmployee(getPageQuery(page), param));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (StringUtils.isNotBlank(employee.getEmployeeName())) {
            try {
                String domainAccount = EmployeeDomainAccountUtils.generateDomainAccount(employee.getEmployeeName());
                int count = this.baseMapper.countLeftDomainAccount(domainAccount);
                if (count != 0) {

                    domainAccount = domainAccount + count + 1;
                }
                employee.setDomainAccount(domainAccount);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                log.error(e.getMessage(), e);
            }
        }
        this.insert(employee);
        return employee;
    }
}

