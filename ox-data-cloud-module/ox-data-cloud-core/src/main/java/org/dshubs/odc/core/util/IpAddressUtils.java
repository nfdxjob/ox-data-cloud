package org.dshubs.odc.core.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author create by wangxian 2022/4/13
 */
public class IpAddressUtils {
    private static final String UN_KNOWN = "unKnown";

    public IpAddressUtils() {
    }

    public static String getIpAddress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
            int index = XFor.indexOf(",");
            return index != -1 ? XFor.substring(0, index) : XFor;
        } else {
            XFor = Xip;
            if (StringUtils.isNotEmpty(Xip) && !"unKnown".equalsIgnoreCase(Xip)) {
                return Xip;
            } else {
                if (StringUtils.isBlank(Xip) || "unKnown".equalsIgnoreCase(Xip)) {
                    XFor = request.getHeader("Proxy-Client-IP");
                }

                if (StringUtils.isBlank(XFor) || "unKnown".equalsIgnoreCase(XFor)) {
                    XFor = request.getHeader("WL-Proxy-Client-IP");
                }

                if (StringUtils.isBlank(XFor) || "unKnown".equalsIgnoreCase(XFor)) {
                    XFor = request.getHeader("HTTP_CLIENT_IP");
                }

                if (StringUtils.isBlank(XFor) || "unKnown".equalsIgnoreCase(XFor)) {
                    XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
                }

                if (StringUtils.isBlank(XFor) || "unKnown".equalsIgnoreCase(XFor)) {
                    XFor = request.getRemoteAddr();
                }

                return XFor;
            }
        }
    }
}
