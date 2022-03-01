package org.dshubs.odc.api.controller.v1;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by wangxian 2022/3/1
 */
@RestController
@RequestMapping("/v1/operation-logs")
@Slf4j
@Api(tags = "操作日志API")
public class OperationLogController {
}
