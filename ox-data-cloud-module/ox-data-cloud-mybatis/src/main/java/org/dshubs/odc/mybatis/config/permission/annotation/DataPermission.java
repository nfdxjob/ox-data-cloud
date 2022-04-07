package org.dshubs.odc.mybatis.config.permission.annotation;

import org.dshubs.odc.mybatis.config.permission.DataPermissionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author create by wangxian 2022/3/3
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPermission {


    DataPermissionType type() default DataPermissionType.GLOBAL;

    PermissionColumn [] columns();
}
