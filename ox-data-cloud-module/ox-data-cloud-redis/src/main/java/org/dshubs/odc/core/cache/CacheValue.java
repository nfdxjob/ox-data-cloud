package org.dshubs.odc.core.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author create by wangxian 2022/4/7
 */
@Target(java.lang.annotation.ElementType.METHOD)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface CacheValue {

    String key();
}
