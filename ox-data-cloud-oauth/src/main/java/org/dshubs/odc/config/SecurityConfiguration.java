package org.dshubs.odc.config;

import org.dshubs.odc.app.service.OauthUserService;
import org.dshubs.odc.core.redis.RedisHelper;
import org.dshubs.odc.custom.CustomAuthenticationProvider;
import org.dshubs.odc.custom.CustomAuthenticationSuccessHandler;
import org.dshubs.odc.custom.CustomUserDetailsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @author create by wangxian 2022/4/13
 */
@Configuration
public class SecurityConfiguration {

    private final OauthUserService oauthUserService;


    private final RedisHelper redisHelper;

    private final DataSource dataSource;

    private final RedisConnectionFactory redisConnectionFactory;

    public SecurityConfiguration(OauthUserService oauthUserService, RedisHelper redisHelper, DataSource dataSource, RedisConnectionFactory redisConnectionFactory) {
        this.oauthUserService = oauthUserService;
        this.redisHelper = redisHelper;
        this.dataSource = dataSource;
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    @Primary
    public ClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setClientDetailsService(clientDetailsService());
        return defaultTokenServices;
    }

    @Bean
    @ConditionalOnMissingBean(CustomUserDetailsService.class)
    public CustomUserDetailsService userDetailsService() {
        return new CustomUserDetailsService(oauthUserService);
    }


    @Bean
    @ConditionalOnMissingBean(CustomAuthenticationProvider.class)
    public CustomAuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(userDetailsService(), passwordEncoder(), redisHelper);
    }

    @Bean
    @ConditionalOnMissingBean(CustomAuthenticationSuccessHandler.class)
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }


}
