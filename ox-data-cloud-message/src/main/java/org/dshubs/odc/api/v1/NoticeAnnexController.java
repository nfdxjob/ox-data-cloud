package org.dshubs.odc.api.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.NoticeAnnexService;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.NoticeAnnex;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告附件API访问
 *
 * @author daisicheng 2022-03-21
 */
@RestController
@RequestMapping("/api/v1/{organizationId}/notice-annexes")
@Api(tags = "公告附件API")
@Slf4j
public class NoticeAnnexController {
    private final NoticeAnnexService noticeAnnexService;

    public NoticeAnnexController(NoticeAnnexService noticeAnnexService) {
        this.noticeAnnexService = noticeAnnexService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<NoticeAnnex>> list(@PathVariable("organizationId") Long organizationId) {
        log.info("所有数据");
        List<NoticeAnnex> result = noticeAnnexService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<NoticeAnnex>> list(@PathVariable("organizationId") Long organizationId, PageRequest page, NoticeAnnex query) {
        log.info("列表查询");
        PageData<NoticeAnnex> result = noticeAnnexService.page(page, query);
        return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<NoticeAnnex> detail(@PathVariable("organizationId") Long organizationId, @PathVariable("id") Long noticeAnnexId) {
        return Results.success(noticeAnnexService.selectById(noticeAnnexId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<NoticeAnnex> create(@PathVariable("organizationId") Long organizationId, @RequestBody @Validated NoticeAnnex noticeAnnex) {
        log.info("创建,参数:{}", noticeAnnex);
        NoticeAnnex result = noticeAnnexService.insert(noticeAnnex);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<NoticeAnnex> update(@PathVariable("organizationId") Long organizationId, @RequestBody @Validated NoticeAnnex noticeAnnex) {
        log.info("更新,参数:{}", noticeAnnex);
        NoticeAnnex result = noticeAnnexService.update(noticeAnnex);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("organizationId") Long organizationId, @PathVariable("id") Long noticeAnnexId) {
        noticeAnnexService.deleteById(noticeAnnexId);
        return Results.success();
    }
}
