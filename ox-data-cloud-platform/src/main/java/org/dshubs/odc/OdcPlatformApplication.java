package org.dshubs.odc;

import org.dshubs.odc.core.resource.annoation.EnableOdcResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
