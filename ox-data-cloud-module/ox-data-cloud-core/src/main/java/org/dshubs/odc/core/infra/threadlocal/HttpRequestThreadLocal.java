package org.dshubs.odc.core.infra.threadlocal;

/**
 *
 * http请求线程变量
 * @author create by wangxian 2022/4/11
 */
public class HttpRequestThreadLocal {
    private static final ThreadLocal<String> REQUEST_ID = new ThreadLocal<>();

    public static void setRequestId(String requestId) {
        REQUEST_ID.set(requestId);
    }

    public static String getRequestId() {
        return REQUEST_ID.get();
    }

    public static void clear() {
        REQUEST_ID.remove();
    }
}
