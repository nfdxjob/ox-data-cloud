package org.dshubs.odc.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.PositionService;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.Position;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位API访问
 *
 * @author wangxian 2022-03-02
 */
@RestController
@RequestMapping("/api/v1/positions")
@Api(tags = "岗位API")
@Slf4j
public class PositionController {
    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<Position>> all() {
        log.info("所有数据");
        List<Position> result = positionService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<Position>> list(PageRequest page, Position query) {
        log.info("列表查询");
        PageData<Position> result = positionService.page(page, query);
        return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<Position> detail(@PathVariable("id") Long positionId) {
        return Results.success(positionService.selectById(positionId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<Position> create(@RequestBody @Validated Position position) {
        log.info("创建,参数:{}", position);
        Position result = positionService.insert(position);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<Position> update(@RequestBody @Validated Position position) {
        log.info("更新,参数:{}", position);
        Position result = positionService.update(position);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long positionId) {
        positionService.deleteById(positionId);
        return Results.success();
    }
}
