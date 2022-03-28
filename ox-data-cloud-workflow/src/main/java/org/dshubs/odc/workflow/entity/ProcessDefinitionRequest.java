package org.dshubs.odc.workflow.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProcessDefinitionRequest {
    private String processDefinitionId;
    private boolean includeProcessInstances = false;
    private Date date;
}
