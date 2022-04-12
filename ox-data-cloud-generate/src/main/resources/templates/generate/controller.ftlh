package ${packageName}.api.controller.v1;

import ${packageName}.domain.entity.${className};
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import ${packageName}.app.service.${className}Service;
import org.dshubs.odc.core.annotation.Permission;
import org.dshubs.odc.core.ips.ResourcesLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ${tableComment}API访问
 *
 * @author ${author} ${date}
 */
@RestController
<#if level ==  "tenant">
@RequestMapping("/api/v1/{organizationId}/${apiBasePath}")
<#else>
@RequestMapping("/api/v1/${apiBasePath}")
</#if>
@Api(tags = "${className}")
@Slf4j
public class ${className}Controller {
    private final ${className}Service ${lowerClassName}Service;

    public ${className}Controller(${className}Service ${lowerClassName}Service) {
        this.${lowerClassName}Service = ${lowerClassName}Service;
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
<#if level ==  "tenant">
    @Permission(level = ResourcesLevel.ORGANIZATION)
    public ResponseEntity<PageData<${className}>> list(@PathVariable("organizationId") Long organizationId, PageRequest page, ${className} query) {
<#else>
    @Permission(level = ResourcesLevel.SITE)
    public ResponseEntity<PageData<${className}>> list(PageRequest page, ${className} query) {
</#if>
         log.info("列表查询");
         PageData<${className}> result = ${lowerClassName}Service.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
<#if level ==  "tenant">
    @Permission(level = ResourcesLevel.ORGANIZATION)
    public ResponseEntity<${className}> detail(@PathVariable("organizationId") Long organizationId, @PathVariable("id") Long ${lowerClassName}Id) {
<#else>
    @Permission(level = ResourcesLevel.SITE)
    public ResponseEntity<${className}> detail(@PathVariable("id") Long ${lowerClassName}Id) {
</#if>
        return Results.success(${lowerClassName}Service.selectById(${lowerClassName}Id));
    }


    @PostMapping
    @ApiOperation("创建")
<#if level ==  "tenant">
    @Permission(level = ResourcesLevel.ORGANIZATION)
    public ResponseEntity<${className}> create(@PathVariable("organizationId") Long organizationId, @RequestBody @Validated ${className} ${lowerClassName}) {
<#else>
    @Permission(level = ResourcesLevel.SITE)
    public ResponseEntity<${className}> create(@RequestBody @Validated ${className} ${lowerClassName}) {
</#if>
        log.info("创建,参数:{}", ${lowerClassName});
        ${className} result = ${lowerClassName}Service.insert(${lowerClassName});
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
<#if level ==  "tenant">
    @Permission(level = ResourcesLevel.ORGANIZATION)
    public ResponseEntity<${className}> update(@PathVariable("organizationId") Long organizationId, @RequestBody @Validated ${className} ${lowerClassName}) {
<#else>
    @Permission(level = ResourcesLevel.SITE)
    public ResponseEntity<${className}> update(@RequestBody @Validated ${className} ${lowerClassName}) {
</#if>
        log.info("更新,参数:{}", ${lowerClassName});
        ${className} result = ${lowerClassName}Service.update(${lowerClassName});
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
<#if level ==  "tenant">
@Permission(level = ResourcesLevel.ORGANIZATION)
    public ResponseEntity<Void> deleteById(@PathVariable("organizationId") Long organizationId, @PathVariable("id") Long ${lowerClassName}Id) {
<#else>
    @Permission(level = ResourcesLevel.SITE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long ${lowerClassName}Id) {
</#if>
        ${lowerClassName}Service.deleteById(${lowerClassName}Id);
        return Results.success();
    }
}
