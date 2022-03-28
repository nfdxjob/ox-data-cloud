package org.dshubs.odc.workflow.entity;

import lombok.Data;

@Data
public class ProcessInstanceDetailResponse extends HistoricProcessInstanceResponse {
    private boolean suspended;
    private String deleteReason;
    private String startUserName;

}
