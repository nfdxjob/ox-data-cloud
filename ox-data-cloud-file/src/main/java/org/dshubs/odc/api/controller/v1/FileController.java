package org.dshubs.odc.api.controller.v1;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.FileService;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.dto.DownloadDTO;
import org.dshubs.odc.vo.FileInfoVO;
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

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileInfoVO> upload(@RequestParam("file") MultipartFile file,
                                             @RequestParam("bucket") String bucket,
                                             @RequestParam("directory") String directory,
                                             @RequestParam("fileName") String fileName) throws Exception{
        return Results.success(fileService.uploadFile(file, bucket, directory, fileName));
    }

    @PostMapping("/upload/with-md5")
    public ResponseEntity<FileInfoVO> uploadWithMd5(@RequestParam("file") MultipartFile file,
                                             @RequestParam("bucket") String bucket,
                                             @RequestParam("directory") String directory,
                                             @RequestParam("fileName") String fileName) throws Exception{
        return Results.success(fileService.uploadFileWithMd5(file, bucket, directory, fileName));
    }

    @PostMapping("/download/by-file-key")
    public void downloadByFileKey(@RequestBody DownloadDTO downloadParam, HttpServletResponse response) throws Exception {
        fileService.download(downloadParam.getFileKey(), response);
    }
}
