package org.dshubs.odc.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description： Flowable主程序
 * @GithubAuthor : zhanglinfu2012
 * @Date: 2022-03-15 22:05
 * @Version: 1.0.0
 * @Copyright: 湖南牛数商智信息科技有限公司
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableScheduling
@EnableFeignClients(basePackages = "org.dshubs.odc.workflow.**")
public class OdcWorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdcWorkflowApplication.class, args);
    }

}
