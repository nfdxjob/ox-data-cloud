package org.dshubs.odc.core.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 核心配置
 * @author create by wangxian 2021/12/5
 */
@ConfigurationProperties(prefix = OdcCoreProperties.CORE_PROPERTIES_PREFIX)
@RefreshScope
@Configuration
@Getter
@Setter
public class OdcCoreProperties {

    public static final String CORE_PROPERTIES_PREFIX = "odc";


    private String oauthJwtSignKey = "odc";

    private Resource resource;



    @Data
    public static class Resource {
        private String pattern = "/v1/*";

        private String ignorePath = "/public/*";
    }
}
