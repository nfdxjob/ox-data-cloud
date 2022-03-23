package org.dshubs.odc.api.controller.v1;

import org.dshubs.odc.domain.entity.AuditOperationLogDetail;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.AuditOperationLogDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作审计日志详情API访问
 *
 * @author wangxian 2022-03-04
 */
@RestController
@RequestMapping("/api/v1/audit-operation-log-details")
@Api(tags = "操作审计日志详情API")
@Slf4j
public class AuditOperationLogDetailController {
    private final AuditOperationLogDetailService auditOperationLogDetailService;

    public AuditOperationLogDetailController(AuditOperationLogDetailService auditOperationLogDetailService) {
        this.auditOperationLogDetailService = auditOperationLogDetailService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<AuditOperationLogDetail>> all() {
        log.info("所有数据");
        List<AuditOperationLogDetail> result = auditOperationLogDetailService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<AuditOperationLogDetail>> list(PageRequest page, AuditOperationLogDetail query) {
         log.info("列表查询");
         PageData<AuditOperationLogDetail> result = auditOperationLogDetailService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<AuditOperationLogDetail> detail(@PathVariable("id") Long auditOperationLogDetailId) {
        return Results.success(auditOperationLogDetailService.selectById(auditOperationLogDetailId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<AuditOperationLogDetail> create(@RequestBody @Validated AuditOperationLogDetail auditOperationLogDetail) {
        log.info("创建,参数:{}", auditOperationLogDetail);
        AuditOperationLogDetail result = auditOperationLogDetailService.insert(auditOperationLogDetail);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<AuditOperationLogDetail> update(@RequestBody @Validated AuditOperationLogDetail auditOperationLogDetail) {
        log.info("更新,参数:{}", auditOperationLogDetail);
        AuditOperationLogDetail result = auditOperationLogDetailService.update(auditOperationLogDetail);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long auditOperationLogDetailId) {
        auditOperationLogDetailService.deleteById(auditOperationLogDetailId);
        return Results.success();
    }
}
