package org.dshubs.odc.core.oauth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * @author create by wangxian 2021/12/5
 */
public class DetailsUtils {
    public static CustomUserDetails getUserDetails() {
        if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof CustomUserDetails) {
                return (CustomUserDetails)principal;
            } else {
                Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
                if (details instanceof OAuth2AuthenticationDetails) {
                    Object decodedDetails = ((OAuth2AuthenticationDetails)details).getDecodedDetails();
                    if (decodedDetails instanceof CustomUserDetails) {
                        return (CustomUserDetails)decodedDetails;
                    }
                }

                return null;
            }
        } else {
            return null;
        }
    }
}
