package org.dshubs.odc.core.permission;

import lombok.Data;

import java.io.Serializable;

/**
 * @author create by wangxian 2022/2/22
 */
@Data
public class PermissionData implements Serializable {
    private String serverName;

    private String path;

    private String method;

    private String requestMethod;

    private String description;

    private String resourceCode;

    private String code;

    private String globalCode;

    private  String permissionLevel;

    private  boolean apiIsPublic;

    private  boolean apiIsLogin;

    private  boolean apiIsSign;
}
