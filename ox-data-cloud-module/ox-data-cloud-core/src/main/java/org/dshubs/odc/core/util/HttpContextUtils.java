package org.dshubs.odc.core.util;

import com.google.common.collect.Maps;
import org.dshubs.odc.core.exception.CommonException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * HttpContextUtils.
 *
 * @author create by wangxian 2022/4/11
 */
public class HttpContextUtils {




    /**
     * 获取request
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        throw new CommonException("HTTP.SERVLET.REQUEST.NULL", "HttpServletRequest is null");
    }


    /**
     * 获取请求参数
     *
     * @param request http请求
     * @return 请求参数
     */
    public static Map<String, String> getRequestParams(HttpServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, String> params = Maps.newHashMap();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String value = request.getParameter(paramName);
            params.put(paramName, value);
        }
        return params;
    }
}
