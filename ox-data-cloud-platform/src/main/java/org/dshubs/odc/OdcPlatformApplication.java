package org.dshubs.odc;

import org.dshubs.odc.core.resource.annoation.EnableButterflyResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 平台基础
 *
 * @author wangxian
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableButterflyResourceServer
public class OdcPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdcPlatformApplication.class, args);
    }

}
