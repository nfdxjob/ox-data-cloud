package org.dshubs.odc.api.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.app.service.SmsServerService;
import org.dshubs.odc.core.util.result.Results;
import org.dshubs.odc.domain.entity.SmsServer;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短信服务API访问
 *
 * @author daisicheng 2022-03-21
 */
@RestController
@RequestMapping("/api/v1/{organizationId}/sms-servers")
@Api(tags = "短信服务API")
@Slf4j
public class SmsServerController {
    private final SmsServerService smsServerService;

    public SmsServerController(SmsServerService smsServerService) {
        this.smsServerService = smsServerService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<SmsServer>> list(@PathVariable("organizationId") Long organizationId) {
        log.info("所有数据");
        List<SmsServer> result = smsServerService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<SmsServer>> list(@PathVariable("organizationId") Long organizationId, PageRequest page, SmsServer query) {
        log.info("列表查询");
        PageData<SmsServer> result = smsServerService.page(page, query);
        return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<SmsServer> detail(@PathVariable("organizationId") Long organizationId, @PathVariable("id") Long smsServerId) {
        return Results.success(smsServerService.selectById(smsServerId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<SmsServer> create(@PathVariable("organizationId") Long organizationId, @RequestBody @Validated SmsServer smsServer) {
        log.info("创建,参数:{}", smsServer);
        SmsServer result = smsServerService.insert(smsServer);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<SmsServer> update(@PathVariable("organizationId") Long organizationId, @RequestBody @Validated SmsServer smsServer) {
        log.info("更新,参数:{}", smsServer);
        SmsServer result = smsServerService.update(smsServer);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("organizationId") Long organizationId, @PathVariable("id") Long smsServerId) {
        smsServerService.deleteById(smsServerId);
        return Results.success();
    }
}
