package org.dshubs.odc.api.controller.v1;

import org.dshubs.odc.domain.entity.LovView;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.LovViewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 值集视图API访问
 *
 * @author wangxian 2022-04-07
 */
@RestController
@RequestMapping("/api/v1/lov-views")
@Api(tags = "值集视图API")
@Slf4j
public class LovViewController {
    private final LovViewService lovViewService;

    public LovViewController(LovViewService lovViewService) {
        this.lovViewService = lovViewService;
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<LovView>> list(PageRequest page, LovView query) {
         log.info("列表查询");
         PageData<LovView> result = lovViewService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<LovView> detail(@PathVariable("id") Long lovViewId) {
        return Results.success(lovViewService.selectById(lovViewId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<LovView> create(@RequestBody @Validated LovView lovView) {
        log.info("创建,参数:{}", lovView);
        LovView result = lovViewService.insert(lovView);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<LovView> update(@RequestBody @Validated LovView lovView) {
        log.info("更新,参数:{}", lovView);
        LovView result = lovViewService.update(lovView);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long lovViewId) {
        lovViewService.deleteById(lovViewId);
        return Results.success();
    }
}
