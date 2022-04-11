package org.dshubs.odc.api.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.EmailServerService;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.EmailServer;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 邮件服务API访问
 *
 * @author daisicheng 2022-03-21
 */
@RestController
@RequestMapping("/api/v1/{organizationId}/email-servers")
@Api(tags = "邮件服务API")
@Slf4j
public class EmailServerController {
    private final EmailServerService emailServerService;

    public EmailServerController(EmailServerService emailServerService) {
        this.emailServerService = emailServerService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<EmailServer>> list(@PathVariable("organizationId") Long organizationId) {
        log.info("所有数据");
        List<EmailServer> result = emailServerService.listMailServer();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<EmailServer>> list(@PathVariable("organizationId") Long organizationId, PageRequest page, EmailServer query) {
        log.info("列表查询");
        PageData<EmailServer> result = emailServerService.pageMailServer(page, query);
        return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<EmailServer> detail(@PathVariable("organizationId") Long organizationId, @PathVariable("id") Long mailServerId) {
        return Results.success(emailServerService.selectById(mailServerId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<EmailServer> create(@PathVariable("organizationId") Long organizationId, @RequestBody @Validated EmailServer emailServer) {
        log.info("创建,参数:{}", emailServer);
        EmailServer result = emailServerService.insert(emailServer);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<EmailServer> update(@PathVariable("organizationId") Long organizationId, @RequestBody @Validated EmailServer emailServer) {
        log.info("更新,参数:{}", emailServer);
        EmailServer result = emailServerService.update(emailServer);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("organizationId") Long organizationId, @PathVariable("id") Long mailServerId) {
        emailServerService.deleteById(mailServerId);
        return Results.success();
    }
}
