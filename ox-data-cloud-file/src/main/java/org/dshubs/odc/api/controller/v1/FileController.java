package org.dshubs.odc.api.controller.v1;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.FileService;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.dto.DownloadDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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

    private final FileService minioFileService;

    public FileController(FileService minioFileService) {
        this.minioFileService = minioFileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,
                                         @RequestParam("bucket") String bucket,
                                         @RequestParam("fileName") String fileName) throws Exception{
        return Results.success(minioFileService.upload(file, bucket, fileName));
    }

    @PostMapping("/download")
    public void download(@RequestBody DownloadDTO downloadParam, HttpServletResponse response) throws Exception {
        minioFileService.download(downloadParam, response);
    }
}
