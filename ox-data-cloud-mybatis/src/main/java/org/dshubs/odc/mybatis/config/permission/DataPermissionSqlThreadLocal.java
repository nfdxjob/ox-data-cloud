package org.dshubs.odc.mybatis.config.permission;

/**
 * @author create by wangxian 2022/3/3
 */
public class DataPermissionSqlThreadLocal {
    private static final ThreadLocal<String> SQL = new ThreadLocal<>();

    public static void setSql(String sql) {
        SQL.set(sql);
    }

    public static String getSql() {
        return SQL.get();
    }

    public static void clear() {
        SQL.remove();
    }
}
