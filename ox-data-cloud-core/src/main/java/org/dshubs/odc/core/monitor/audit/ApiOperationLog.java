package org.dshubs.odc.core.monitor.audit;

import java.lang.annotation.*;

/**
 * API操作日志
 *
 * @author create by wangxian 2022/3/1
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiOperationLog {

    String value();
}
