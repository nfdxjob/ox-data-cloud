package org.dshubs.odc.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author create by wangxian 2021/12/29
 */
@Configuration
@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private static final String[] PERMIT_PATHS = new String[]{
            "/",
            "/login",
            "/login/**",
            "/open-bind",
            "/token/**",
            "/pass-page/**", "/admin/**", "/static/**", "/password/**", "/admin/**", "/static/**", "/saml/metadata", "/actuator/**"};

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] permitPaths = ArrayUtils.addAll(PERMIT_PATHS);
        http.authorizeRequests().antMatchers(permitPaths).permitAll().
                and().formLogin().permitAll().loginPage("/login")
                .failureHandler((httpServletRequest, httpServletResponse, e) -> log.error(e.getMessage(), e))
                .successHandler((httpServletRequest, httpServletResponse, authentication)
                        -> log.info("{} login success", authentication.getName())).and().logout().deleteCookies("access_token").invalidateHttpSession(true)
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> log.info("{} logout success", authentication.getName())).and()
                .authorizeRequests().anyRequest().authenticated().
                and().exceptionHandling().authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> log.error(e.getMessage(), e)).and().csrf().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}