package org.dshubs.odc.api.controller.v1;

import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.oauth.CustomUserDetails;
import org.dshubs.odc.core.oauth.DetailsUtils;
import org.dshubs.odc.core.util.Results;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by wangxian 2022/2/21
 */
@RequestMapping("/api/v1/user-details")
@RestController
@Slf4j
public class UserDetailsController {


    @GetMapping("/current")
    public ResponseEntity<CustomUserDetails> getCurrentUser(){
        CustomUserDetails userDetails = DetailsUtils.getUserDetails();
        return Results.success(userDetails);
    }
}
