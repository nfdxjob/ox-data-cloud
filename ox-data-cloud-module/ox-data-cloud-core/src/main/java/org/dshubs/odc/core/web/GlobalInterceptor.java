package org.dshubs.odc.core.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.core.infra.Constant;
import org.dshubs.odc.core.infra.threadlocal.HttpRequestThreadLocal;
import org.dshubs.odc.core.util.LogMDCUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author create by wangxian 2022/4/11
 */
@Configuration
@Slf4j
public class GlobalInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestId = request.getHeader(Constant.REQUEST_ID_HEADER);
        if (StringUtils.isBlank(requestId)) {
            requestId = UUID.randomUUID().toString();
        }
        HttpRequestThreadLocal.setRequestId(requestId);
        LogMDCUtils.set(Constant.REQUEST_ID_HEADER, requestId);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpRequestThreadLocal.clear();
        LogMDCUtils.clear();
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
