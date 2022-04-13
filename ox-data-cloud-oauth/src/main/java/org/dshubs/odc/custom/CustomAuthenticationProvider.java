package org.dshubs.odc.custom;

import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.redis.RedisHelper;
import org.dshubs.odc.infra.util.LoginUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;

/**
 * @author create by wangxian 2022/4/13
 */
@Slf4j
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {


    private final CustomUserDetailsService customUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final RedisHelper redisHelper;

    public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService,
                                        PasswordEncoder passwordEncoder,
                                        RedisHelper redisHelper) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.redisHelper = redisHelper;
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return super.supports(authentication) &&
                UsernamePasswordAuthenticationToken.class.getTypeName().equals(authentication.getTypeName());

    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        log.info("自定义检查");
        checkCaptcha(authentication);
        //可做自定义验证
        checkPassword(userDetails, authentication);
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        log.info("自定义获取用户,username:{}", username);
        return customUserDetailsService.loadUserByUsername(username);
    }


    protected void checkPassword(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String presentedPassword = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }
    }

    protected void checkCaptcha(UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Map<String, Object> parameter = (Map) authentication.getDetails();
        String captcha = getParameterForMap(parameter, LoginUtils.FIELD_CAPTCHA);
        String captchaKey = getParameterForMap(parameter, LoginUtils.FIELD_CAPTCHA_KEY);
        log.debug("captcha:{},captchaKey:{}", captcha, captchaKey);


    }


    protected String getParameterForMap(Map<String, Object> parameters, String key) {
        return Optional.ofNullable(parameters.get(key)).map(Object::toString).orElse(null);
    }
}
