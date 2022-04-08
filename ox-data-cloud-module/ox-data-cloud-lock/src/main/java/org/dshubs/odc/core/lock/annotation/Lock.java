package org.dshubs.odc.core.lock.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁注解,用在方法
 *
 * @author create by wangxian 2022/4/7
 */
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Lock {
    /**
     * 锁的key
     *
     * @return key
     */
    String name() default "";
    
    long waitTime() default 60L;

    long leaseTime() default 60L;

    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
