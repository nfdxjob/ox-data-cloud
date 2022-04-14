package org.dshubs.odc.config;

import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.JsonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置
 *
 * @author wangxian
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("*.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    log.info("登录成功:{},{}", authentication.getName(), JsonUtils.getInstance().toJson(authentication));
                    response.sendRedirect("http://localhost:8010/home");
                })
                .permitAll()
                .and()
                .requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**","/login")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().headers().frameOptions().disable()
                .and().csrf().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
