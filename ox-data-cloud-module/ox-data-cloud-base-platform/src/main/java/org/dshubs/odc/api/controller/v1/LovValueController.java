package org.dshubs.odc.api.controller.v1;

import org.dshubs.odc.domain.entity.LovValue;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.LovValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 独立值集数据API访问
 *
 * @author wangxian 2022-04-07
 */
@RestController
@RequestMapping("/api/v1/lov-values")
@Api(tags = "独立值集数据API")
@Slf4j
public class LovValueController {
    private final LovValueService lovValueService;

    public LovValueController(LovValueService lovValueService) {
        this.lovValueService = lovValueService;
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<LovValue>> list(PageRequest page, LovValue query) {
         log.info("列表查询");
         PageData<LovValue> result = lovValueService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<LovValue> detail(@PathVariable("id") Long lovValueId) {
        return Results.success(lovValueService.selectById(lovValueId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<LovValue> create(@RequestBody @Validated LovValue lovValue) {
        log.info("创建,参数:{}", lovValue);
        LovValue result = lovValueService.insert(lovValue);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<LovValue> update(@RequestBody @Validated LovValue lovValue) {
        log.info("更新,参数:{}", lovValue);
        LovValue result = lovValueService.update(lovValue);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long lovValueId) {
        lovValueService.deleteById(lovValueId);
        return Results.success();
    }
}
