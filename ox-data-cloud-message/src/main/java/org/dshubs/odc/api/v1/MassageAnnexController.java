package org.dshubs.odc.api.v1;

import org.dshubs.odc.domain.entity.MassageAnnex;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.MassageAnnexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告附件API访问
 *
 * @author daisicheng 2022-03-21
 */
@RestController
@RequestMapping("/api/v1/massage-annexes")
@Api(tags = "公告附件API")
@Slf4j
public class MassageAnnexController {
    private final MassageAnnexService massageAnnexService;

    public MassageAnnexController(MassageAnnexService massageAnnexService) {
        this.massageAnnexService = massageAnnexService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<MassageAnnex>> list() {
        log.info("所有数据");
        List<MassageAnnex> result = massageAnnexService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<MassageAnnex>> list(PageRequest page, MassageAnnex query) {
         log.info("列表查询");
         PageData<MassageAnnex> result = massageAnnexService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<MassageAnnex> detail(@PathVariable("id") Long massageAnnexId) {
        return Results.success(massageAnnexService.selectById(massageAnnexId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<MassageAnnex> create(@RequestBody @Validated MassageAnnex massageAnnex) {
        log.info("创建,参数:{}", massageAnnex);
        MassageAnnex result = massageAnnexService.insert(massageAnnex);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<MassageAnnex> update(@RequestBody @Validated MassageAnnex massageAnnex) {
        log.info("更新,参数:{}", massageAnnex);
        MassageAnnex result = massageAnnexService.update(massageAnnex);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long massageAnnexId) {
        massageAnnexService.deleteById(massageAnnexId);
        return Results.success();
    }
}
