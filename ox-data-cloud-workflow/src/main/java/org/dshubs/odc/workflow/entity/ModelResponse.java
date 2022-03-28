package org.dshubs.odc.workflow.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ModelResponse {
    private String id;

    private String name;
    private String key;
    private String category;
    private String description;
    private String tenantId;
    private String editor;

    private Date createTime;
    private Date lastUpdateTime;
    private Boolean deployed;
    private Integer version;
}
