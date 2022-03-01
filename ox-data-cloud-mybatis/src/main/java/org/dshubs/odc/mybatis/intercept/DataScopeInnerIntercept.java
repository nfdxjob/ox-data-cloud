//package org.dshubs.odc.mybatis.intercept;
//
//import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.session.RowBounds;
//
//import java.sql.SQLException;
//
///**
// * Mybatis-Plus数据范围拦截
// *
// * @author create by wangxian 2021/12/8
// */
//@Slf4j
//public class DataScopeInnerIntercept implements InnerInterceptor {
//
//    @Override
//    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
//        InnerInterceptor.super.beforeQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql);
//    }
//}
