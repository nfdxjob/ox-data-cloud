package org.dshubs.odc.workflow.entity;

import lombok.Data;

import java.util.Date;

@Data
public class FlowNodeResponse {
    /**
     * 节点id
     */
    private String nodeId;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 执行人的id
     */
    private String userId;
    /**
     * 执行人姓名
     */
    private String userName;

    /**
     * 任务节点结束时间
     */
    private Date endTime;

}
