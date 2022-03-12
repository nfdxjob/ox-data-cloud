package org.dshubs.odc.custom;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.oauth.CustomUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author create by wangxian 2022/2/21
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("login name:{}", s);
        return new CustomUserDetails(s, passwordEncoder.encode("123456"), Lists.newArrayList(new SimpleGrantedAuthority("admin")));
    }
}
