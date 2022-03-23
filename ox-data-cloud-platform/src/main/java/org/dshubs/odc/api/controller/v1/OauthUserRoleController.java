package org.dshubs.odc.api.controller.v1;

import org.dshubs.odc.domain.entity.OauthUserRole;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.OauthUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户角色API访问
 *
 * @author wangxian 2022-03-04
 */
@RestController
@RequestMapping("/api/v1/oauth-user-roles")
@Api(tags = "用户角色API")
@Slf4j
public class OauthUserRoleController {
    private final OauthUserRoleService oauthUserRoleService;

    public OauthUserRoleController(OauthUserRoleService oauthUserRoleService) {
        this.oauthUserRoleService = oauthUserRoleService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<OauthUserRole>> all() {
        log.info("所有数据");
        List<OauthUserRole> result = oauthUserRoleService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<OauthUserRole>> list(PageRequest page, OauthUserRole query) {
         log.info("列表查询");
         PageData<OauthUserRole> result = oauthUserRoleService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<OauthUserRole> detail(@PathVariable("id") Long oauthUserRoleId) {
        return Results.success(oauthUserRoleService.selectById(oauthUserRoleId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<OauthUserRole> create(@RequestBody @Validated OauthUserRole oauthUserRole) {
        log.info("创建,参数:{}", oauthUserRole);
        OauthUserRole result = oauthUserRoleService.insert(oauthUserRole);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<OauthUserRole> update(@RequestBody @Validated OauthUserRole oauthUserRole) {
        log.info("更新,参数:{}", oauthUserRole);
        OauthUserRole result = oauthUserRoleService.update(oauthUserRole);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long oauthUserRoleId) {
        oauthUserRoleService.deleteById(oauthUserRoleId);
        return Results.success();
    }
}
