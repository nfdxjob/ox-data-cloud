package org.dshubs.odc;

import org.dshubs.odc.core.resource.annoation.EnableButterflyResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 文档服务,提供动态渲染word,word转pdf等功能
 *
 * @author wangxian
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableButterflyResourceServer
public class OdcDocumentApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdcDocumentApplication.class, args);
    }

}
