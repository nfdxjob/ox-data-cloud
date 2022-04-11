package org.dshubs.odc.core.util;

import org.slf4j.MDC;

/**
 * 扩展slf4j MDC功能
 *
 * @author wangxian 2022/04/11
 */
public class LogMDCUtils {

    public static void set(String key, String value) {
        MDC.put(key, value);
    }

    public static String get(String key) {
        return MDC.get(key);
    }

    public static void clear(){
        MDC.clear();
    }
}
