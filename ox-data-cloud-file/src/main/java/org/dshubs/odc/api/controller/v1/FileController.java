package org.dshubs.odc.api.controller.v1;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
    public String upload(@RequestParam("file") MultipartFile file) throws IOException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, RegionConflictException {
        List<Bucket> buckets = minioClient.listBuckets();
        minioClient.putObject("test", "test.png", file.getInputStream(), new PutObjectOptions(file.getSize(),
                PutObjectOptions.MIN_MULTIPART_SIZE));

        return "OK";
    }
}
