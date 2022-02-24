package org.dshubs.odc.mybatis.intercept;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

/**
 * 数据范围拦截
 *
 * @author create by wangxian 2021/12/8
 */
@Slf4j
public class DataScopeIntercept implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }

}
