package org.dshubs.odc.core.resource.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * 资源服务器配置
 *
 * @author create by wangxian 2021/12/5
 */
@ConditionalOnProperty(name = "odc.resource.server.enable", matchIfMissing = true)
@Slf4j
@AllArgsConstructor
public class OdcResourceServerConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable().csrf().disable().authorizeRequests().antMatchers(new String[]{"/monitor/**"}).permitAll();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }



}

