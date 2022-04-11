package org.dshubs.odc.api.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.NoticeService;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.Notice;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告信息API访问
 *
 * @author daisicheng 2022-03-21
 */
@RestController
@RequestMapping("/api/v1/{organizationId}/notices")
@Api(tags = "公告信息API")
@Slf4j
public class NoticeController {
    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<Notice>> list(@PathVariable("organizationId") Long organizationId) {
        log.info("所有数据");
        List<Notice> result = noticeService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<Notice>> list(@PathVariable("organizationId") Long organizationId, PageRequest page, Notice query) {
        log.info("列表查询");
        PageData<Notice> result = noticeService.page(page, query);
        return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<Notice> detail(@PathVariable("organizationId") Long organizationId, @PathVariable("id") Long noticeId) {
        return Results.success(noticeService.selectById(noticeId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<Notice> create(@PathVariable("organizationId") Long organizationId, @RequestBody @Validated Notice notice) {
        log.info("创建,参数:{}", notice);
        Notice result = noticeService.insert(notice);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<Notice> update(@PathVariable("organizationId") Long organizationId, @RequestBody @Validated Notice notice) {
        log.info("更新,参数:{}", notice);
        Notice result = noticeService.update(notice);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("organizationId") Long organizationId, @PathVariable("id") Long noticeId) {
        noticeService.deleteById(noticeId);
        return Results.success();
    }
}
