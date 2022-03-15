package org.dshubs.odc.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author create by wangxian 2021/12/29
 */
@Configuration
@Slf4j
@EnableWebSecurity
@Order(2147483642)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private static final String[] PERMIT_PATHS = new String[]{"/login", "/login/**", "/open-bind", "/token/**",
            "/pass-page/**", "/admin/**", "/static/**", "/password/**", "/admin/**", "/static/**", "/saml/metadata", "/actuator/**"};

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] permitPaths = ArrayUtils.addAll(PERMIT_PATHS);
        http.authorizeRequests().antMatchers(permitPaths).permitAll().and().authorizeRequests().anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) {
                log.error(e.getMessage(), e);
            }
        });
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}