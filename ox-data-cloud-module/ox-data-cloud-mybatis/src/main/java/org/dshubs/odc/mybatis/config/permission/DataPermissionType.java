package org.dshubs.odc.mybatis.config.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author create by wangxian 2022/3/3
 */
@AllArgsConstructor
@Getter
public enum DataPermissionType {
    /**
     * 全局
     */
    GLOBAL("GLOBAL"),

    /**
     * 用户
     */
    USER("USER");


    private final String value;
}
