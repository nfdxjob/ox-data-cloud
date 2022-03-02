package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dshubs.odc.api.vo.EmployeeListQueryVO;
import org.dshubs.odc.domain.entity.Employee;


/**
 * 员工信息数据访问层
 *
 * @author wangxian 2022-03-02
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * List employee
     * @param page page
     * @param param search condition
     * @return Page
     */
    Page<Employee> listEmployee(Page<Employee> page, @Param("p") EmployeeListQueryVO param);
}
