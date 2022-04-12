package org.dshubs.odc.api.controller.v1;

import org.dshubs.odc.domain.entity.UserEmployee;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.UserEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户员工API访问
 *
 * @author wangxian 2022-03-04
 */
@RestController
@RequestMapping("/api/v1/user-employees")
@Api(tags = "User Employee")
@Slf4j
public class UserEmployeeController {
    private final UserEmployeeService userEmployeeService;

    public UserEmployeeController(UserEmployeeService userEmployeeService) {
        this.userEmployeeService = userEmployeeService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<UserEmployee>> all() {
        log.info("所有数据");
        List<UserEmployee> result = userEmployeeService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<UserEmployee>> list(PageRequest page, UserEmployee query) {
         log.info("列表查询");
         PageData<UserEmployee> result = userEmployeeService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<UserEmployee> detail(@PathVariable("id") Long userEmployeeId) {
        return Results.success(userEmployeeService.selectById(userEmployeeId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<UserEmployee> create(@RequestBody @Validated UserEmployee userEmployee) {
        log.info("创建,参数:{}", userEmployee);
        UserEmployee result = userEmployeeService.insert(userEmployee);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<UserEmployee> update(@RequestBody @Validated UserEmployee userEmployee) {
        log.info("更新,参数:{}", userEmployee);
        UserEmployee result = userEmployeeService.update(userEmployee);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long userEmployeeId) {
        userEmployeeService.deleteById(userEmployeeId);
        return Results.success();
    }
}
