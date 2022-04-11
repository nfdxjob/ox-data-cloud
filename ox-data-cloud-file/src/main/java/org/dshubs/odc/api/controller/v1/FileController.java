package org.dshubs.odc.api.controller.v1;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.FileService;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.dto.DeleteDTO;
import org.dshubs.odc.dto.DownloadDTO;
import org.dshubs.odc.dto.UpdateDTO;
import org.dshubs.odc.vo.FileInfoVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
                                             @RequestParam(value = "directory", required = false) String directory,
                                             @RequestParam(value = "fileName",required = false) String fileName) throws Exception{
        return Results.success(fileService.uploadFile(file, bucket, directory, fileName));
    }

    @PostMapping("/upload/with-md5")
    public ResponseEntity<FileInfoVO> uploadWithMd5(@RequestParam("file") MultipartFile file,
                                             @RequestParam("bucket") String bucket,
                                             @RequestParam(value = "directory", required = false) String directory,
                                             @RequestParam(value = "fileName", required = false) String fileName) throws Exception{
        return Results.success(fileService.uploadFileWithMd5(file, bucket, directory, fileName));
    }

    @GetMapping("/post-policy")
    public ResponseEntity<Map<String, String>> postPolicy(@RequestParam(value = "bucket", required = false) String bucket) {
        return Results.success(fileService.getPostPolicy(bucket));
    }

    @DeleteMapping("/delete/by-file-key")
    public ResponseEntity<Void> deleteByFileKey(@RequestBody DeleteDTO deleteDTO) {
        fileService.deleteByFileKey(deleteDTO.getFileKey());
        return Results.success();
    }

    @PutMapping("/update/by-file-key")
    public ResponseEntity<FileInfoVO> updateByFileKey(UpdateDTO updateDTO) {
        return Results.success(fileService.updateByFileKey(updateDTO));
    }

    @PostMapping("/download/by-file-key")
    public void downloadByFileKey(@RequestBody DownloadDTO downloadParam, HttpServletResponse response) {
        fileService.download(downloadParam.getFileKey(), response);
    }

    @PostMapping("/download/{fileResourceId}/{fileVersion}")
    public void downloadByVersion(@PathVariable("fileResourceId") Long fileResourceId,@PathVariable("fileVersion") String fileVersion, HttpServletResponse response) {
        fileService.download(fileResourceId, fileVersion, response);
    }
}
