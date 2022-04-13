package org.dshubs.odc;

import org.dshubs.odc.core.resource.annoation.EnableOdcResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 平台基础
 *
 * @author wangxian
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableOdcResourceServer
public class OdcPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdcPlatformApplication.class, args);
    }

}
