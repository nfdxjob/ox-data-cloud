package org.dshubs.odc;

import org.dshubs.odc.core.resource.annoation.EnableOdcResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangxian
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableOdcResourceServer
public class OdcMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdcMessageApplication.class, args);
    }

}
