package org.dshubs.odc.workflow.entity;

import lombok.Data;

/**
 * @author guodong
 * @date 2020年3月24日
 */
@Data
public class IdentityRequest {
    private String processDefinitionId;
    private String taskId;
    private String identityId;
    private String identityType;
}
