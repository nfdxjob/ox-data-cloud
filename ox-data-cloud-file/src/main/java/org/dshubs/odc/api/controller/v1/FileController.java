package org.dshubs.odc.api.controller.v1;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    private final MinioClient minioClient;

    public FileController(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception{
//        minioClient.putObject("test", "test.png", file.getInputStream(), new PutObjectOptions(file.getSize(),
//                PutObjectOptions.MIN_MULTIPART_SIZE));
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket("test").build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("test").build());
        }
        minioClient.putObject(PutObjectArgs.builder().bucket("test").object(file.getOriginalFilename()).stream(
                        file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build());
        return "OK";
    }

}
