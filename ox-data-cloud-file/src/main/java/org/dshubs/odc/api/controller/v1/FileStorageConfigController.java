package org.dshubs.odc.api.controller.v1;

import org.dshubs.odc.domain.entity.FileStorageConfig;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.FileStorageConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文件存储配置API访问
 *
 * @author zhou 2022-04-06
 */
@RestController
@RequestMapping("/api/v1/file-storage-configs")
@Api(tags = "文件存储配置API")
@Slf4j
public class FileStorageConfigController {
    private final FileStorageConfigService fileStorageConfigService;

    public FileStorageConfigController(FileStorageConfigService fileStorageConfigService) {
        this.fileStorageConfigService = fileStorageConfigService;
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<FileStorageConfig>> list(PageRequest page, FileStorageConfig query) {
         log.info("列表查询");
         PageData<FileStorageConfig> result = fileStorageConfigService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<FileStorageConfig> detail(@PathVariable("id") Long fileStorageConfigId) {
        return Results.success(fileStorageConfigService.selectById(fileStorageConfigId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<FileStorageConfig> create(@RequestBody @Validated FileStorageConfig fileStorageConfig) {
        log.info("创建,参数:{}", fileStorageConfig);
        FileStorageConfig result = fileStorageConfigService.insert(fileStorageConfig);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<FileStorageConfig> update(@RequestBody @Validated FileStorageConfig fileStorageConfig) {
        log.info("更新,参数:{}", fileStorageConfig);
        FileStorageConfig result = fileStorageConfigService.update(fileStorageConfig);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long fileStorageConfigId) {
        fileStorageConfigService.deleteById(fileStorageConfigId);
        return Results.success();
    }
}
