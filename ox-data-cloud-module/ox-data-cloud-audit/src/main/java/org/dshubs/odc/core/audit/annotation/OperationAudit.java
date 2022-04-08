package org.dshubs.odc.core.audit.annotation;

import java.lang.annotation.*;

/**
 * 操作审计
 *
 * @author create by wangxian 2022/3/1
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationAudit {

    String value();

    String description() default "";

}
