package org.dshubs.odc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author wangxian
 */
@SpringBootApplication
@EnableEurekaServer
public class OdcRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdcRegisterApplication.class, args);
    }

}
