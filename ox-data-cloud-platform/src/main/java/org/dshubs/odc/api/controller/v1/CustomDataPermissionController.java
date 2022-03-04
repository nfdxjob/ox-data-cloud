package org.dshubs.odc.api.controller.v1;

import org.dshubs.odc.domain.entity.CustomDataPermission;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.CustomDataPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据权限API访问
 *
 * @author wangxian 2022-03-04
 */
@RestController
@RequestMapping("/api/v1/custom-data-permissions")
@Api(tags = "数据权限API")
@Slf4j
public class CustomDataPermissionController {
    private final CustomDataPermissionService customDataPermissionService;

    public CustomDataPermissionController(CustomDataPermissionService customDataPermissionService) {
        this.customDataPermissionService = customDataPermissionService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<CustomDataPermission>> list() {
        log.info("所有数据");
        List<CustomDataPermission> result = customDataPermissionService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<CustomDataPermission>> list(PageRequest page, CustomDataPermission query) {
         log.info("列表查询");
         PageData<CustomDataPermission> result = customDataPermissionService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<CustomDataPermission> detail(@PathVariable("id") Long opfmCustomDataPermissionId) {
        return Results.success(customDataPermissionService.selectById(opfmCustomDataPermissionId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<CustomDataPermission> create(@RequestBody @Validated CustomDataPermission customDataPermission) {
        log.info("创建,参数:{}", customDataPermission);
        CustomDataPermission result = customDataPermissionService.insert(customDataPermission);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<CustomDataPermission> update(@RequestBody @Validated CustomDataPermission customDataPermission) {
        log.info("更新,参数:{}", customDataPermission);
        CustomDataPermission result = customDataPermissionService.update(customDataPermission);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long opfmCustomDataPermissionId) {
        customDataPermissionService.deleteById(opfmCustomDataPermissionId);
        return Results.success();
    }
}
