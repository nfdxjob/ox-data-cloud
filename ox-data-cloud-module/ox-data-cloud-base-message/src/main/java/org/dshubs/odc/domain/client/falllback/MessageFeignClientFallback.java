package org.dshubs.odc.domain.client.falllback;

import feign.hystrix.FallbackFactory;
import org.dshubs.odc.domain.client.MessageFeignClient;

/**
 * @author create by wangxian 2022/4/12
 */
public class MessageFeignClientFallback implements FallbackFactory<MessageFeignClient> {
    @Override
    public MessageFeignClient create(Throwable throwable) {
        return new MessageFeignClient() {

        };
    }
}
