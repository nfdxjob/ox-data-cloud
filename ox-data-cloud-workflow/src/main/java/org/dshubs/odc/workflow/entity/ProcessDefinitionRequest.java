package org.dshubs.odc.workflow.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author guodong
 * @date 2020年3月24日
 */
@Data
public class ProcessDefinitionRequest {
    private String processDefinitionId;
    private boolean includeProcessInstances = false;
    private Date date;
}
