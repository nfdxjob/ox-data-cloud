package org.dshubs.odc.api.controller.v1;

import org.dshubs.odc.domain.entity.AuditOperationLog;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.AuditOperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作审计日志API访问
 *
 * @author wangxian 2022-03-04
 */
@RestController
@RequestMapping("/api/v1/audit-operation-logs")
@Api(tags = "操作审计日志API")
@Slf4j
public class AuditOperationLogController {
    private final AuditOperationLogService auditOperationLogService;

    public AuditOperationLogController(AuditOperationLogService auditOperationLogService) {
        this.auditOperationLogService = auditOperationLogService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<AuditOperationLog>> all() {
        log.info("所有数据");
        List<AuditOperationLog> result = auditOperationLogService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<AuditOperationLog>> list(PageRequest page, AuditOperationLog query) {
         log.info("列表查询");
         PageData<AuditOperationLog> result = auditOperationLogService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<AuditOperationLog> detail(@PathVariable("id") Long auditOperationLogId) {
        return Results.success(auditOperationLogService.selectById(auditOperationLogId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<AuditOperationLog> create(@RequestBody @Validated AuditOperationLog auditOperationLog) {
        log.info("创建,参数:{}", auditOperationLog);
        AuditOperationLog result = auditOperationLogService.insert(auditOperationLog);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<AuditOperationLog> update(@RequestBody @Validated AuditOperationLog auditOperationLog) {
        log.info("更新,参数:{}", auditOperationLog);
        AuditOperationLog result = auditOperationLogService.update(auditOperationLog);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long auditOperationLogId) {
        auditOperationLogService.deleteById(auditOperationLogId);
        return Results.success();
    }
}
