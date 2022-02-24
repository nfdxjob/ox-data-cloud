package org.dshubs.odc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * Gateway 网关服务
 *
 * @author wangxian
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OdcGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdcGatewayApplication.class, args);
    }

}
