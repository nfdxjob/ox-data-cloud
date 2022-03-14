package org.dshubs.odc.mybatis.config.permission.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.dshubs.odc.core.util.JsonUtils;
import org.dshubs.odc.mybatis.config.permission.DataPermissionSqlThreadLocal;
import org.dshubs.odc.mybatis.config.permission.DataPermissionType;
import org.dshubs.odc.mybatis.config.permission.annotation.DataPermission;
import org.dshubs.odc.mybatis.config.permission.annotation.PermissionColumn;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.reflect.Method;

/**
 * 权限拦截切面
 *
 * @author wangxian
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@Aspect
@Slf4j
public class DataPermissionAspectj {


    @Value("${spring.application.name}")
    private String applicationName;


    @Pointcut("@annotation(org.dshubs.odc.mybatis.config.permission.annotation.DataPermission)")
    public void pointcut() {

    }


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            log.debug("application name:{},method:{}", applicationName, method.getName());
            DataPermission annotation = method.getAnnotation(DataPermission.class);
            if (annotation != null) {
                //类型,可根据不同类型做不同处理
                DataPermissionType type = annotation.type();
                //注解上的描述
                PermissionColumn[] permissionColumns = annotation.columns();
                log.debug("permission type:{}", type.getValue());
                if (ArrayUtils.isNotEmpty(permissionColumns)) {
                    StringBuilder tmpSql = new StringBuilder();
                    int count = 0;
                    for (PermissionColumn pc : permissionColumns) {
                        String alias = pc.alias();
                        String column = pc.column();
                        log.info("alias:{},column:{}", alias, column);
                        if (StringUtils.isNotBlank(alias)) {
                            alias += ".";
                        }
                        if (count != 0) {
                            tmpSql.append(" AND ");
                        }
                        tmpSql.append(" ").append(alias).append(column);
                        count++;
                    }
                    String sql = tmpSql.toString();
                    log.debug("SQL:{}", sql);
                    DataPermissionSqlThreadLocal.setSql(sql);
                }


            }
            //可以做数据审计
            Object result = point.proceed();
            DataPermissionSqlThreadLocal.clear();
            log.debug("SQL执行结果,result:{}", JsonUtils.getInstance().toJson(result));
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }

    }


}
