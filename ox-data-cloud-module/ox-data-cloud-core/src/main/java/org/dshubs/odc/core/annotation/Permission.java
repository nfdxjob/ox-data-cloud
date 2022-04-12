package org.dshubs.odc.core.annotation;

import org.dshubs.odc.core.ips.ResourcesLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限
 * @author create by wangxian 2021/12/4
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    String code() default "";

    String[] roles() default {};

    ResourcesLevel level() default ResourcesLevel.SITE;

    boolean apiIsPublic() default false;

    boolean apiIsLogin() default false;

    boolean apiIsAdmin() default false;

    boolean apiIsSign() default false;


}
