package org.dshubs.odc.workflow.entity;

import lombok.Data;

/**
 * @author guodong
 * @date 2020年3月24日
 */
@Data
public class ProcessInstanceDetailResponse extends HistoricProcessInstanceResponse {
    private boolean suspended;
    private String deleteReason;
    private String startUserName;

}
