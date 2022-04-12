package org.dshubs.odc.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.TenantService;
import org.dshubs.odc.core.annotation.Permission;
import org.dshubs.odc.core.ips.ResourcesLevel;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.Tenant;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author create by wangxian 2022/2/19
 */
@RestController
@RequestMapping("/api/v1/tenants")
@Slf4j
@Api(tags = "Tenant Site")
public class TenantSiteController {
    private final TenantService tenantService;

    public TenantSiteController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @ApiOperation("所有租户")
    @GetMapping("/all")
    @Permission(level = ResourcesLevel.SITE)
    public ResponseEntity<List<Tenant>> listAll() {
        log.info("租户所有列表");
        List<Tenant> tenants = tenantService.list();
        return Results.success(tenants);
    }

    @ApiOperation("租户列表")
    @GetMapping("/list")
    @Permission(level = ResourcesLevel.SITE)
    public ResponseEntity<PageData<Tenant>> list(PageRequest page, Tenant query) {
        log.info("租户列表");
        PageData<Tenant> tenants = tenantService.page(page, query);
        return Results.success(tenants);
    }

    @ApiOperation("租户详情")
    @GetMapping("/{id}")
    @Permission(level = ResourcesLevel.SITE)
    public ResponseEntity<Tenant> detail(@PathVariable("id") Long id) {
        log.info("租户详情,id:{}", id);
        Tenant tenant = tenantService.selectById(id);
        return Results.success(tenant);
    }

    @ApiOperation("创建租户")
    @PostMapping
    @Permission(level = ResourcesLevel.SITE)
    public ResponseEntity<Tenant> create(@RequestBody @Validated Tenant tenant) {
        log.info("创建租户,tenant:[{}]", tenant);
        return Results.success(tenantService.insert(tenant));
    }

    @ApiOperation("更新租户")
    @PutMapping
    @Permission(level = ResourcesLevel.SITE)
    public ResponseEntity<Tenant> update(@RequestBody @Validated Tenant tenant) {
        log.info("更新租户,tenant:[{}]", tenant);
        return Results.success(tenantService.update(tenant));
    }

    @ApiOperation("删除租户")
    @DeleteMapping("/{id}")
    @Permission(level = ResourcesLevel.SITE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        log.info("删除租户,id:{}", id);
        tenantService.deleteById(id);
        return Results.success();
    }
}
