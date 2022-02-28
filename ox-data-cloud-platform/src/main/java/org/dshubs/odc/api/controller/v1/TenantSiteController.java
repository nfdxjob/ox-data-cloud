package org.dshubs.odc.api.controller.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.annotation.Permission;
import org.dshubs.odc.core.ips.ResourcesLevel;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.Tenant;
import org.dshubs.odc.app.service.TenantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author create by wangxian 2022/2/19
 */
@RestController
@RequestMapping("/v1/tenants")
@Slf4j
@Api(tags = "租户管理API")
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
    public ResponseEntity<Page<Tenant>> list(Page<Tenant> page) {
        log.info("租户列表");
        Page<Tenant> tenants = tenantService.page(page);
        return Results.success(tenants);
    }

    @ApiOperation("创建租户")
    @PostMapping
    @Permission(level = ResourcesLevel.SITE)
    public ResponseEntity<Tenant> create(@RequestBody Tenant tenant) {
        log.info("创建租户,tenant:[{}]", tenant);
        tenantService.save(tenant);
        return Results.success(tenant);
    }
}
