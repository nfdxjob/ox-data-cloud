package org.dshubs.odc.workflow.entity;

import lombok.Data;

/**
 * @author guodong
 * @date 2020年8月30日
 */
@Data
public class ModelRequest {
    private String id;
    private String key;
    private String name;
    private String category;
    private String description;
    private String tenantId;
    private String editor;
    private boolean newVersion;
    private boolean cascade;
}
