package org.dshubs.odc.config;

import org.dshubs.odc.app.service.OauthUserService;
import org.dshubs.odc.core.redis.RedisHelper;
import org.dshubs.odc.custom.CustomAuthenticationProvider;
import org.dshubs.odc.custom.CustomUserDetailsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author create by wangxian 2022/4/13
 */
@Configuration
public class SecurityConfiguration {

    private final OauthUserService oauthUserService;

    private final PasswordEncoder passwordEncoder;

    private final RedisHelper redisHelper;

    public SecurityConfiguration(OauthUserService oauthUserService, PasswordEncoder passwordEncoder, RedisHelper redisHelper) {
        this.oauthUserService = oauthUserService;
        this.passwordEncoder = passwordEncoder;
        this.redisHelper = redisHelper;
    }

    @Bean
    @ConditionalOnMissingBean(CustomUserDetailsService.class)
    public CustomUserDetailsService userDetailsService() {
        return new CustomUserDetailsService(oauthUserService);
    }


    @Bean
    @ConditionalOnMissingBean(CustomAuthenticationProvider.class)
    public CustomAuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(userDetailsService(), passwordEncoder, redisHelper);
    }
}
