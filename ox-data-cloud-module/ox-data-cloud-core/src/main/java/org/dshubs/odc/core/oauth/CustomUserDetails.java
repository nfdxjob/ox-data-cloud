package org.dshubs.odc.core.oauth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author create by wangxian 2021/12/5
 */
@Getter
@Setter
public class CustomUserDetails extends User implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 当前角色ID
     */
    private Long roleId;

    private List<Long> roleIds;

    /**
     * 当前租户ID
     */
    private Long organizationId;

    private List<Long> tenantIds;

    private Boolean isAdmin;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }


}
