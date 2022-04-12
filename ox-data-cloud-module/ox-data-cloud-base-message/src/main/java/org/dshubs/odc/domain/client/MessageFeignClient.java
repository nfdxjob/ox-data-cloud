package org.dshubs.odc.domain.client;

import org.dshubs.odc.domain.client.falllback.MessageFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author create by wangxian 2022/3/23
 */
@FeignClient(name = "${ox.service.message.name:message-service}", fallbackFactory = MessageFeignClientFallback.class)
public interface MessageFeignClient {


}
