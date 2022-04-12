package org.dshubs.odc.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.api.vo.EmployeeListQueryVO;
import org.dshubs.odc.app.service.EmployeeService;
import org.dshubs.odc.app.service.UserEmployeeService;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.Employee;
import org.dshubs.odc.domain.entity.OauthUser;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工信息API访问
 *
 * @author wangxian 2022-03-02
 */
@RestController
@RequestMapping("/api/v1/employees")
@Api(tags = "Employee")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    private final UserEmployeeService userEmployeeService;

    public EmployeeController(EmployeeService employeeService,
                              UserEmployeeService userEmployeeService) {
        this.employeeService = employeeService;
        this.userEmployeeService = userEmployeeService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> all() {
        log.info("所有数据");
        List<Employee> result = employeeService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<Employee>> list(PageRequest page, EmployeeListQueryVO query) {
        log.info("列表查询");
        PageData<Employee> result = employeeService.listEmployee(page, query);
        return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<Employee> detail(@PathVariable("id") Long employeeId) {
        return Results.success(employeeService.selectById(employeeId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<Employee> create(@RequestBody @Validated Employee employee) {
        log.info("创建,参数:{}", employee);
        Employee result = employeeService.createEmployee(employee);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<Employee> update(@RequestBody @Validated Employee employee) {
        log.info("更新,参数:{}", employee);
        Employee result = employeeService.update(employee);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long employeeId) {
        employeeService.deleteById(employeeId);
        return Results.success();
    }


    @PostMapping("/convert-user/{id}")
    @ApiOperation("将员工转换成系统用户")
    public ResponseEntity<OauthUser> convertToUser(@PathVariable("id") Long id) {
        OauthUser oauthUser = userEmployeeService.employeeConvertToUser(id);
        return Results.success(oauthUser);
    }
}
