package org.dshubs.odc.api.controller.v1;

import org.dshubs.odc.domain.entity.FileResource;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.FileResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文件资源API访问
 *
 * @author zhou 2022-04-06
 */
@RestController
@RequestMapping("/api/v1/file-resources")
@Api(tags = "文件资源API")
@Slf4j
public class FileResourceController {
    private final FileResourceService fileResourceService;

    public FileResourceController(FileResourceService fileResourceService) {
        this.fileResourceService = fileResourceService;
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<FileResource>> list(PageRequest page, FileResource query) {
         log.info("列表查询");
         PageData<FileResource> result = fileResourceService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<FileResource> detail(@PathVariable("id") Long fileResourceId) {
        return Results.success(fileResourceService.selectById(fileResourceId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<FileResource> create(@RequestBody @Validated FileResource fileResource) {
        log.info("创建,参数:{}", fileResource);
        FileResource result = fileResourceService.insert(fileResource);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<FileResource> update(@RequestBody @Validated FileResource fileResource) {
        log.info("更新,参数:{}", fileResource);
        FileResource result = fileResourceService.update(fileResource);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long fileResourceId) {
        fileResourceService.deleteById(fileResourceId);
        return Results.success();
    }
}
