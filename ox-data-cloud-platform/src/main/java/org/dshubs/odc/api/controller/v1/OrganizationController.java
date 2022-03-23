package org.dshubs.odc.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.OrganizationService;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.Organization;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织部门API访问
 *
 * @author wangxian 2022-03-02
 */
@RestController
@RequestMapping("/api/v1/organizations")
@Api(tags = "组织部门API")
@Slf4j
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<Organization>> all() {
        log.info("所有数据");
        List<Organization> result = organizationService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<Organization>> list(PageRequest page, Organization query) {
        log.info("列表查询");
        PageData<Organization> result = organizationService.page(page, query);
        return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<Organization> detail(@PathVariable("id") Long organizationId) {
        return Results.success(organizationService.selectById(organizationId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<Organization> create(@RequestBody @Validated Organization organization) {
        log.info("创建,参数:{}", organization);
        Organization result = organizationService.insert(organization);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<Organization> update(@RequestBody @Validated Organization organization) {
        log.info("更新,参数:{}", organization);
        Organization result = organizationService.update(organization);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long organizationId) {
        organizationService.deleteById(organizationId);
        return Results.success();
    }
}
