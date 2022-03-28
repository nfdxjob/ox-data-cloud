package org.dshubs.odc.workflow.entity;

import lombok.Data;

@Data
public class IdentityRequest {
    private String processDefinitionId;
    private String taskId;
    private String identityId;
    private String identityType;
}
