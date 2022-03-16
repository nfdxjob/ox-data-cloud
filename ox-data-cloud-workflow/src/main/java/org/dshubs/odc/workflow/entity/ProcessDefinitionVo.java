package org.dshubs.odc.workflow.entity;

import lombok.Data;

@Data
public class ProcessDefinitionVo {
    private String category;
    private String categoryName;
    private String processDefinitionKey;
    private String processDefinitionName;
    private Long count;
}
