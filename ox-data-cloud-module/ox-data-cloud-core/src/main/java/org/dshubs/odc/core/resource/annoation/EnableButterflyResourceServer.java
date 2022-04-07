package org.dshubs.odc.core.resource.annoation;

import org.dshubs.odc.core.resource.config.ButterflyResourceServerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author create by wangxian 2021/12/5
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ButterflyResourceServerConfiguration.class})
public @interface EnableButterflyResourceServer {
}
