package org.dshubs.odc.api.v1;

import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.annotation.Permission;
import org.dshubs.odc.core.ips.ResourcesLevel;
import org.dshubs.odc.core.util.Results;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by wangxian 2021/12/4
 */
@RestController
@RequestMapping("/v1/{organizationId}/message-templates")
@Slf4j
public class MessageTemplateController {

    @GetMapping
    @Permission(level = ResourcesLevel.ORGANIZATION)
    public ResponseEntity<?> list(@PathVariable("organizationId") Long tenantId
                                  ){
        return Results.success(tenantId);
    }
}
