package org.dshubs.odc.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.api.vo.LovValueVO;
import org.dshubs.odc.app.service.LovService;
import org.dshubs.odc.core.annotation.Permission;
import org.dshubs.odc.core.ips.ResourcesLevel;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.Lov;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 值集API访问
 *
 * @author wangxian 2022-04-07
 */
@RestController
@RequestMapping("/api/v1/lovs")
@Api(tags = "Lov")
@Slf4j
public class LovController {
    private final LovService lovService;

    public LovController(LovService lovService) {
        this.lovService = lovService;
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    @Permission(apiIsAdmin = true)
    public ResponseEntity<PageData<Lov>> list(PageRequest page, Lov query) {
        log.info("列表查询");
        PageData<Lov> result = lovService.page(page, query);
        return Results.success(result);
    }

    @ApiOperation("值集数据查询")
    @GetMapping("/data")
    @Permission(level = ResourcesLevel.SITE, apiIsLogin = true)
    public ResponseEntity<List<LovValueVO>> list(@RequestParam("lovCode") String lovCode) {
        log.info("值集数据查询,lovCode:{}", lovCode);
        List<LovValueVO> result = lovService.listLovData(lovCode);
        return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    @Permission(level = ResourcesLevel.SITE, apiIsLogin = true)
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
