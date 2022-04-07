//package org.dshubs.odc.mybatis.intercept;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.plugin.Intercepts;
//import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.session.RowBounds;
//
///**
// * 数据范围拦截
// *
// * @author create by wangxian 2021/12/8
// */
//@Slf4j
//@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
//public class DataScopeIntercept implements Interceptor {
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        log.debug("自定义数据权限拦截");
//        Object[] args = invocation.getArgs();
//        log.debug("args:{}", args);
//        return invocation.proceed();
//    }
//
//}
