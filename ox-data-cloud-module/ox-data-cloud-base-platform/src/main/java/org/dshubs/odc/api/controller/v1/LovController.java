package org.dshubs.odc.api.controller.v1;

import org.dshubs.odc.domain.entity.Lov;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.LovService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 值集API访问
 *
 * @author wangxian 2022-04-07
 */
@RestController
@RequestMapping("/api/v1/lovs")
@Api(tags = "值集API")
@Slf4j
public class LovController {
    private final LovService lovService;

    public LovController(LovService lovService) {
        this.lovService = lovService;
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<Lov>> list(PageRequest page, Lov query) {
         log.info("列表查询");
         PageData<Lov> result = lovService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<Lov> detail(@PathVariable("id") Long lovId) {
        return Results.success(lovService.selectById(lovId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<Lov> create(@RequestBody @Validated Lov lov) {
        log.info("创建,参数:{}", lov);
        Lov result = lovService.insert(lov);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<Lov> update(@RequestBody @Validated Lov lov) {
        log.info("更新,参数:{}", lov);
        Lov result = lovService.update(lov);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long lovId) {
        lovService.deleteById(lovId);
        return Results.success();
    }
}
