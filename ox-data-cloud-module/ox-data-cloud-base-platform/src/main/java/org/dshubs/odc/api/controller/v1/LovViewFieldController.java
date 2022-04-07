package org.dshubs.odc.api.controller.v1;

import org.dshubs.odc.domain.entity.LovViewField;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.LovViewFieldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 值集视图字段API访问
 *
 * @author wangxian 2022-04-07
 */
@RestController
@RequestMapping("/api/v1/lov-view-fields")
@Api(tags = "值集视图字段API")
@Slf4j
public class LovViewFieldController {
    private final LovViewFieldService lovViewFieldService;

    public LovViewFieldController(LovViewFieldService lovViewFieldService) {
        this.lovViewFieldService = lovViewFieldService;
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<LovViewField>> list(PageRequest page, LovViewField query) {
         log.info("列表查询");
         PageData<LovViewField> result = lovViewFieldService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<LovViewField> detail(@PathVariable("id") Long lovViewFieldId) {
        return Results.success(lovViewFieldService.selectById(lovViewFieldId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<LovViewField> create(@RequestBody @Validated LovViewField lovViewField) {
        log.info("创建,参数:{}", lovViewField);
        LovViewField result = lovViewFieldService.insert(lovViewField);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<LovViewField> update(@RequestBody @Validated LovViewField lovViewField) {
        log.info("更新,参数:{}", lovViewField);
        LovViewField result = lovViewFieldService.update(lovViewField);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long lovViewFieldId) {
        lovViewFieldService.deleteById(lovViewFieldId);
        return Results.success();
    }
}
