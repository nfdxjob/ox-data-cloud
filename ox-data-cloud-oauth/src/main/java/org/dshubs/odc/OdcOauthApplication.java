package org.dshubs.odc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangxian
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OdcOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdcOauthApplication.class, args);
    }

}
