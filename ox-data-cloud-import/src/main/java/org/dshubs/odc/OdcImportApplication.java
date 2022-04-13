package org.dshubs.odc;

import org.dshubs.odc.core.resource.annoation.EnableOdcResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 通用导入服务
 *
 * @author wangxian
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableOdcResourceServer
public class OdcImportApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdcImportApplication.class, args);
    }

}
