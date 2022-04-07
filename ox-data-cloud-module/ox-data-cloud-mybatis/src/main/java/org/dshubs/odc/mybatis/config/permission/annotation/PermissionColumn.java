package org.dshubs.odc.mybatis.config.permission.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author create by wangxian 2022/3/3
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionColumn {

    /**
     * 表别名
     *
     * @return 别名
     */
    String alias() default "";

    /**
     * 权限列
     *
     * @return 字段名称
     */
    String column();
}
