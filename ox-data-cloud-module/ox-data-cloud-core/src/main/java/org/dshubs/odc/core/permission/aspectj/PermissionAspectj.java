package org.dshubs.odc.core.permission.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.dshubs.odc.core.annotation.Permission;
import org.dshubs.odc.core.exception.CommonException;
import org.dshubs.odc.core.oauth.CustomUserDetails;
import org.dshubs.odc.core.oauth.DetailsUtils;
import org.dshubs.odc.core.util.HttpContextUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 系统权限控制切面
 *
 * @author create by wangxian 2022/4/12
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@Aspect
@Slf4j
public class PermissionAspectj {
    @Value("${spring.application.name}")
    private String applicationName;

    @Pointcut("@annotation(org.dshubs.odc.core.annotation.Permission)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
            String requestURI = request.getRequestURI();
            log.debug("application name:{},method:{},requestURI:{}", applicationName, method.getName(), requestURI);
            Permission annotation = method.getAnnotation(Permission.class);
            if (annotation != null) {
                if (annotation.apiIsPublic()) {
                    return point.proceed();
                }
                if (annotation.apiIsLogin()) {
                    CustomUserDetails userDetails = DetailsUtils.getUserDetails();
                    if (userDetails != null) {
                        return point.proceed();
                    }
                    throw new CommonException("no.login","no login");
                }
                if (annotation.apiIsAdmin()) {
                    CustomUserDetails userDetails = DetailsUtils.getUserDetails();
                    if (userDetails != null && userDetails.getIsAdmin()) {
                        return point.proceed();
                    }
                    throw new CommonException("no.admin","no admin");
                }
                //TODO 权限控制
                return point.proceed();
            }
            throw new CommonException("no.permission", "没有权限");

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }

    }
}
