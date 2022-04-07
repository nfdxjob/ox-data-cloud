package org.dshubs.odc.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author create by wangxian 2021/12/30
 */
@Component
@Slf4j
public class ApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("{} started success, args:{}", applicationName, ArrayUtils.toString(event.getArgs()));
    }
}
