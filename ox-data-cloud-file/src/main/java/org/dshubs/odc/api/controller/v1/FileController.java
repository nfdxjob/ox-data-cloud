package org.dshubs.odc.api.controller.v1;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.FileStorageConfigService;
import org.dshubs.odc.constant.enums.StorageType;
import org.dshubs.odc.domain.entity.FileStorageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件操作
 *
 * @author Mr.zhou 2022/4/6
 **/
@RequestMapping("/api/v1/file")
@Api(tags = "文件操作")
@Slf4j
@RestController
public class FileController {

    @Autowired
    private FileStorageConfigService fileStorageConfigService;

    @GetMapping("/hello")
    public FileStorageConfig hello() {
       return fileStorageConfigService.getFileStoreByType(StorageType.MINIO.getType());
    }

    @GetMapping("/hello2")
    public FileStorageConfig hello2() {
       return fileStorageConfigService.getDefaultFileStore();
    }
}
