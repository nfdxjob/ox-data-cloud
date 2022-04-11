package org.dshubs.odc.core.util;


import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class HttpContextUtilsTest {
    @Test
    public void getHttpServletRequestTest() {
        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(new MockHttpServletRequest());
        RequestContextHolder.setRequestAttributes(requestAttributes);
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        System.out.println(request.getRemoteAddr());
    }


    @Test
    public void getRequestParamsTest() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.addParameter("param1", "value1");
        mockHttpServletRequest.addParameter("param2", "value2");
        mockHttpServletRequest.addParameter("param3", "value3");
        Map<String, String> requestParams = HttpContextUtils.getRequestParams(mockHttpServletRequest);
        requestParams.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}