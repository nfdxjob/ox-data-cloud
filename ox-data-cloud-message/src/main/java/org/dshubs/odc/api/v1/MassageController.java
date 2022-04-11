package org.dshubs.odc.api.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.MassageService;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.Massage;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 消息信息API访问
 *
 * @author daisicheng 2022-03-21
 */
@RestController
@RequestMapping("/api/v1/{organizationId}/massages")
@Api(tags = "消息信息API")
@Slf4j
public class MassageController {
    private final MassageService massageService;

    public MassageController(MassageService massageService) {
        this.massageService = massageService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<Massage>> list(@PathVariable("organizationId") Long organizationId) {
        log.info("所有数据");
        List<Massage> result = massageService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<Massage>> list(@PathVariable("organizationId") Long organizationId, PageRequest page, Massage query) {
        log.info("列表查询");
        PageData<Massage> result = massageService.page(page, query);
        return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<Massage> detail(@PathVariable("organizationId") Long organizationId, @PathVariable("id") Long massageId) {
        return Results.success(massageService.selectById(massageId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<Massage> create(@PathVariable("organizationId") Long organizationId, @RequestBody @Validated Massage massage) {
        log.info("创建,参数:{}", massage);
        Massage result = massageService.insert(massage);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<Massage> update(@PathVariable("organizationId") Long organizationId, @RequestBody @Validated Massage massage) {
        log.info("更新,参数:{}", massage);
        Massage result = massageService.update(massage);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("organizationId") Long organizationId, @PathVariable("id") Long massageId) {
        massageService.deleteById(massageId);
        return Results.success();
    }
}
