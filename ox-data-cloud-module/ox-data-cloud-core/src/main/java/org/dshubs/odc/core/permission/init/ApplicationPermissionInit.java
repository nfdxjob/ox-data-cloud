package org.dshubs.odc.core.permission.init;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.permission.ApplicationPermissionParse;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

/**
 * 应用权限初始化
 *
 * @author create by wangxian 2021/12/30
 */
@Component
@AllArgsConstructor
@Slf4j
public class ApplicationPermissionInit implements SmartInitializingSingleton {
    private final ApplicationPermissionParse applicationPermissionParse;

    @Override
    public void afterSingletonsInstantiated() {
        log.info("permission init");
        this.applicationPermissionParse.parse();

    }
}
