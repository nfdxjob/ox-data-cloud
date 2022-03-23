package org.dshubs.odc.api.v1;

import org.dshubs.odc.domain.entity.UserMessage;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.dshubs.odc.app.service.UserMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户消息API访问
 *
 * @author daisicheng 2022-03-21
 */
@RestController
@RequestMapping("/api/v1/user-messages")
@Api(tags = "用户消息API")
@Slf4j
public class UserMessageController {
    private final UserMessageService userMessageService;

    public UserMessageController(UserMessageService userMessageService) {
        this.userMessageService = userMessageService;
    }

    @ApiOperation("所有数据")
    @GetMapping("/all")
    public ResponseEntity<List<UserMessage>> list() {
        log.info("所有数据");
        List<UserMessage> result = userMessageService.list();
        return Results.success(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResponseEntity<PageData<UserMessage>> list(PageRequest page, UserMessage query) {
         log.info("列表查询");
         PageData<UserMessage> result = userMessageService.page(page, query);
         return Results.success(result);
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID获取")
    public ResponseEntity<UserMessage> detail(@PathVariable("id") Long userMessageId) {
        return Results.success(userMessageService.selectById(userMessageId));
    }


    @PostMapping
    @ApiOperation("创建")
    public ResponseEntity<UserMessage> create(@RequestBody @Validated UserMessage userMessage) {
        log.info("创建,参数:{}", userMessage);
        UserMessage result = userMessageService.insert(userMessage);
        return Results.success(result);
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseEntity<UserMessage> update(@RequestBody @Validated UserMessage userMessage) {
        log.info("更新,参数:{}", userMessage);
        UserMessage result = userMessageService.update(userMessage);
        return Results.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long userMessageId) {
        userMessageService.deleteById(userMessageId);
        return Results.success();
    }
}
