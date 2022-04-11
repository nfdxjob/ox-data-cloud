package org.dshubs.odc.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.FileEditLogService;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.FileEditLog;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 文件更改版本表API访问
 *
 * @author Mr.zhou 2022-04-11
 */
@RestController
@RequestMapping("/api/v1/file-edit-logs")
@Api(tags = "文件更改版本表API")
@Slf4j
public class FileEditLogController {
    private final FileEditLogService fileEditLogService;

    public FileEditLogController(FileEditLogService fileEditLogService) {
        this.fileEditLogService = fileEditLogService;
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<FileEditLog>> list(PageRequest page, FileEditLog query) {
         log.info("列表查询");
         PageData<FileEditLog> result = fileEditLogService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<FileEditLog> detail(@PathVariable("id") Long fileEditLogId) {
        return Results.success(fileEditLogService.selectById(fileEditLogId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<FileEditLog> create(@RequestBody @Validated FileEditLog fileEditLog) {
        log.info("创建,参数:{}", fileEditLog);
        FileEditLog result = fileEditLogService.insert(fileEditLog);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<FileEditLog> update(@RequestBody @Validated FileEditLog fileEditLog) {
        log.info("更新,参数:{}", fileEditLog);
        FileEditLog result = fileEditLogService.update(fileEditLog);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long fileEditLogId) {
        fileEditLogService.deleteById(fileEditLogId);
        return Results.success();
    }
}
