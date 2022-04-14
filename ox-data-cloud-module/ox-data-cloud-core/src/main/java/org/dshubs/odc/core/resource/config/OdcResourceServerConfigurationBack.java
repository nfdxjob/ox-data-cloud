//package org.dshubs.odc.core.resource.config;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//import org.springframework.web.cors.CorsUtils;
//
//import javax.sql.DataSource;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 资源服务器配置
// *
// * @author create by wangxian 2021/12/5
// */
//@ConditionalOnProperty(name = "odc.resource.server.enable", matchIfMissing = true)
//@Slf4j
//@AllArgsConstructor
//@Configuration
//@EnableResourceServer
//public class OdcResourceServerConfigurationBack extends ResourceServerConfigurerAdapter {
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public TokenStore tokenStore() {
//        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
//        redisTokenStore.setPrefix("oauth:session:");
//        return redisTokenStore;
//    }
//
//    private TokenEnhancerChain initTokenEnhancerChain() {
//        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
//        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
//        return tokenEnhancerChain;
//    }
//
//    @Bean
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setSupportRefreshToken(true);
//        defaultTokenServices.setTokenEnhancer(initTokenEnhancerChain());
//        defaultTokenServices.setTokenStore(tokenStore());
//        defaultTokenServices.setClientDetailsService(clientDetailsService());
//        return defaultTokenServices;
//    }
//
//    @Bean
//    public ClientDetailsService clientDetailsService() {
//        return new JdbcClientDetailsService(dataSource);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.tokenServices(tokenServices());
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        //所有请求必须认证通过
//        http.authorizeRequests()
//                //下边的路径放行
//                .antMatchers(
//                        "/*.html",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/swagger-ui/index.html",
//                        "/swagger-resources/**",
//                        "/doc.html",
//                        "/*/api-docs",
//                        "/actuator/**",
//                        "/oauth/authorize",
//                        "/login",
//                        "/",
//                        "/home"
//                ).permitAll()
//                .antMatchers(HttpMethod.OPTIONS)
//                .permitAll()
//                .requestMatchers(CorsUtils::isPreFlightRequest)
//                .permitAll()
//                .anyRequest().authenticated();
//    }
//
//}
//
