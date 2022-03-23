package org.dshubs.odc.api.v1;

import org.dshubs.odc.domain.entity.MailServer;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.MailServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 邮件服务API访问
 *
 * @author daisicheng 2022-03-21
 */
@RestController
@RequestMapping("/api/v1/mail-servers")
@Api(tags = "邮件服务API")
@Slf4j
public class MailServerController {
    private final MailServerService mailServerService;

    public MailServerController(MailServerService mailServerService) {
        this.mailServerService = mailServerService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<MailServer>> list() {
        log.info("所有数据");
        List<MailServer> result = mailServerService.listMailServer();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<MailServer>> list(PageRequest page, MailServer query) {
        log.info("列表查询");
        PageData<MailServer> result = mailServerService.pageMailServer(page, query);
        return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<MailServer> detail(@PathVariable("id") Long mailServerId) {
        return Results.success(mailServerService.selectById(mailServerId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<MailServer> create(@RequestBody @Validated MailServer mailServer) {
        log.info("创建,参数:{}", mailServer);
        MailServer result = mailServerService.insert(mailServer);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<MailServer> update(@RequestBody @Validated MailServer mailServer) {
        log.info("更新,参数:{}", mailServer);
        MailServer result = mailServerService.update(mailServer);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long mailServerId) {
        mailServerService.deleteById(mailServerId);
        return Results.success();
    }
}
