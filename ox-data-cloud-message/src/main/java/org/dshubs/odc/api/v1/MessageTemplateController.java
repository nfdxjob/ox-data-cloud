package org.dshubs.odc.api.v1;

import org.dshubs.odc.domain.entity.MessageTemplate;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.MessageTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 消息模板API访问
 *
 * @author daisicheng 2022-03-21
 */
@RestController
@RequestMapping("/api/v1/message-templates")
@Api(tags = "消息模板API")
@Slf4j
public class MessageTemplateController {
    private final MessageTemplateService messageTemplateService;

    public MessageTemplateController(MessageTemplateService messageTemplateService) {
        this.messageTemplateService = messageTemplateService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<MessageTemplate>> list() {
        log.info("所有数据");
        List<MessageTemplate> result = messageTemplateService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<MessageTemplate>> list(PageRequest page, MessageTemplate query) {
         log.info("列表查询");
         PageData<MessageTemplate> result = messageTemplateService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<MessageTemplate> detail(@PathVariable("id") Long messageTemplateId) {
        return Results.success(messageTemplateService.selectById(messageTemplateId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<MessageTemplate> create(@RequestBody @Validated MessageTemplate messageTemplate) {
        log.info("创建,参数:{}", messageTemplate);
        MessageTemplate result = messageTemplateService.insert(messageTemplate);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<MessageTemplate> update(@RequestBody @Validated MessageTemplate messageTemplate) {
        log.info("更新,参数:{}", messageTemplate);
        MessageTemplate result = messageTemplateService.update(messageTemplate);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long messageTemplateId) {
        messageTemplateService.deleteById(messageTemplateId);
        return Results.success();
    }
}
