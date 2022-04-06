package org.dshubs.odc;

import io.minio.MinioClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ButterflyMessageApplicationTests {

    @Autowired
    private MinioClient minioClient;

    @Test
    void contextLoads() {
        System.out.println(minioClient);
    }

}
