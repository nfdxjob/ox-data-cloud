package org.dshubs.odc.core.ips;

import lombok.Getter;

/**
 * @author create by wangxian 2021/12/4
 */
@Getter
public enum ResourcesLevel {
    /**
     * 平台层
     */
    SITE("site"),
    /**
     * 租户层
     */
    ORGANIZATION("organization"),

    /**
     * 项目层
     */
    PROJECT("project"),

    /**
     * 用户层
     */
    USER("user");

    private final String value;

    ResourcesLevel(String value){
        this.value = value;
    }
}
