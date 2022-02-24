package org.dshubs.odc.custom;

import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.oauth.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author create by wangxian 2022/2/21
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("login name:{}",s);
        return new CustomUserDetails(s,null,null);
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123123"));
    }
}
