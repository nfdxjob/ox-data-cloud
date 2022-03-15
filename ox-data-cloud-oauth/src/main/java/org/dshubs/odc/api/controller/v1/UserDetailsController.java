package org.dshubs.odc.api.controller.v1;

import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.oauth.CustomUserDetails;
import org.dshubs.odc.core.oauth.DetailsUtils;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author create by wangxian 2022/2/21
 */
@RequestMapping("/api/v1/user-details")
@RestController
@Slf4j
public class UserDetailsController {


    private final TokenStore tokenStore;

    public UserDetailsController(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @GetMapping("/current")
    public ResponseEntity<CustomUserDetails> getCurrentUser() {
        CustomUserDetails userDetails = DetailsUtils.getUserDetails();
        return Results.success(userDetails);
    }


    @DeleteMapping("/logout/{token}")
    public ResponseEntity<Void> logout(@PathVariable("token") String token) {
        DefaultOAuth2AccessToken accessToken = new DefaultOAuth2AccessToken(token);
        tokenStore.removeAccessToken(accessToken);
        return Results.success();
    }

    @GetMapping("/on-line")
    public ResponseEntity<List<OAuth2Authentication>> onLine() {
        Collection<OAuth2AccessToken> oAuth2AccessTokens = tokenStore.findTokensByClientId("butterfly");
        List<OAuth2Authentication> result = new ArrayList<>();
        for (OAuth2AccessToken oAuth2AccessToken : oAuth2AccessTokens) {
            OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(oAuth2AccessToken);
            result.add(oAuth2Authentication);
        }
        return Results.success(result);
    }

}
