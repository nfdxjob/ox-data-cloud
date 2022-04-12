package org.dshubs.odc.api.controller.v1;

import org.dshubs.odc.domain.entity.OauthUser;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.OauthUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息API访问
 *
 * @author wangxian 2022-03-04
 */
@RestController
@RequestMapping("/api/v1/oauth-users")
@Api(tags = "Oauth User")
@Slf4j
public class OauthUserController {
    private final OauthUserService oauthUserService;

    public OauthUserController(OauthUserService oauthUserService) {
        this.oauthUserService = oauthUserService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<OauthUser>> all() {
        log.info("所有数据");
        List<OauthUser> result = oauthUserService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<OauthUser>> list(PageRequest page, OauthUser query) {
         log.info("列表查询");
         PageData<OauthUser> result = oauthUserService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<OauthUser> detail(@PathVariable("id") Long oauthUserId) {
        return Results.success(oauthUserService.selectById(oauthUserId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<OauthUser> create(@RequestBody @Validated OauthUser oauthUser) {
        log.info("创建,参数:{}", oauthUser);
        OauthUser result = oauthUserService.insert(oauthUser);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<OauthUser> update(@RequestBody @Validated OauthUser oauthUser) {
        log.info("更新,参数:{}", oauthUser);
        OauthUser result = oauthUserService.update(oauthUser);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long oauthUserId) {
        oauthUserService.deleteById(oauthUserId);
        return Results.success();
    }
}
