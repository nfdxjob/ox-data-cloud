package org.dshubs.odc;

import org.dshubs.odc.core.resource.annoation.EnableButterflyResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 文件服务,提供文件存储,下载等功能
 *
 * @author wangxian
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableButterflyResourceServer
public class OdcFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdcFileApplication.class, args);
    }

}
