package org.dshubs.odc.custom;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.OauthUserService;
import org.dshubs.odc.core.oauth.CustomUserDetails;
import org.dshubs.odc.domain.entity.OauthUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author create by wangxian 2022/2/21
 */
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final OauthUserService oauthUserService;

    public CustomUserDetailsService(OauthUserService oauthUserService) {
        this.oauthUserService = oauthUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("login name is:{}", s);
        OauthUser oauthUser = oauthUserService.findByUsernameOrEmail(s);
        if (oauthUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(s, oauthUser.getPassword(), Lists.newArrayList(new SimpleGrantedAuthority("admin")));
        customUserDetails.setOrganizationId(oauthUser.getTenantId());
        customUserDetails.setEmail(oauthUser.getEmail());
        customUserDetails.setIsAdmin(oauthUser.getAdmin());
        customUserDetails.setRealName(oauthUser.getRealName());
        customUserDetails.setUserId(oauthUser.getUserId());
        customUserDetails.setNickname(oauthUser.getNickName());
        return customUserDetails;
    }
}
