package org.dshubs.odc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

/**
 * @author create by wangxian 2022/4/14
 */
@Configuration
@EnableResourceServer
@Order(1)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.setSharedObject(ClientDetailsService.class, clientDetailsService);
        http.antMatcher("/api/**").authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("default");
    }
}
