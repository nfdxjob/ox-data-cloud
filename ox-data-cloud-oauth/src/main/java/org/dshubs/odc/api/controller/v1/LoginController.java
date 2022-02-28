package org.dshubs.odc.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


/**
 * @author create by wangxian 2021/12/29
 */
@RestController
@Slf4j
@Api(tags = "登录API")
public class LoginController {


    @ApiOperation("生成验证码Key")
    @GetMapping("/captcha/key")
    public ResponseEntity<String> generateCaptchaKey(){
        String uuid = UUID.randomUUID().toString().replace("-","").toLowerCase();
        return Results.success(uuid);
    }
}
