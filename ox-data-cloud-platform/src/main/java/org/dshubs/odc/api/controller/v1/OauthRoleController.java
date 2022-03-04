package org.dshubs.odc.api.controller.v1;

import org.dshubs.odc.domain.entity.OauthRole;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.OauthRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色API访问
 *
 * @author wangxian 2022-03-04
 */
@RestController
@RequestMapping("/api/v1/oauth-roles")
@Api(tags = "角色API")
@Slf4j
public class OauthRoleController {
    private final OauthRoleService oauthRoleService;

    public OauthRoleController(OauthRoleService oauthRoleService) {
        this.oauthRoleService = oauthRoleService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<OauthRole>> list() {
        log.info("所有数据");
        List<OauthRole> result = oauthRoleService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<OauthRole>> list(PageRequest page, OauthRole query) {
         log.info("列表查询");
         PageData<OauthRole> result = oauthRoleService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<OauthRole> detail(@PathVariable("id") Long oauthRoleId) {
        return Results.success(oauthRoleService.selectById(oauthRoleId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<OauthRole> create(@RequestBody @Validated OauthRole oauthRole) {
        log.info("创建,参数:{}", oauthRole);
        OauthRole result = oauthRoleService.insert(oauthRole);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<OauthRole> update(@RequestBody @Validated OauthRole oauthRole) {
        log.info("更新,参数:{}", oauthRole);
        OauthRole result = oauthRoleService.update(oauthRole);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long oauthRoleId) {
        oauthRoleService.deleteById(oauthRoleId);
        return Results.success();
    }
}
