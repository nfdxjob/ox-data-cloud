package org.dshubs.odc.workflow.service;

import org.dshubs.odc.workflow.entity.ProcessInstanceRequest;
import org.dshubs.odc.workflow.entity.query.ProcessInstanceQueryVo;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.List;

/**
 * Flowable 流程实例Service
 *
 * @author 湖南牛数商智信息科技有限公司
 */
public interface ProcessInstanceService {
    /**
     * 查询单一流程实例
     */
    ProcessInstance getProcessInstanceById(String processInstanceId);

    /**
     * 查询单一历史流程实例
     */
    HistoricProcessInstance getHistoricProcessInstanceById(String processInstanceId);

    /**
     * 启动流程实例
     */
    String start(ProcessInstanceRequest processInstanceRequest);

    /**
     * 删除流程实例
     */
    void delete(String processInstanceId, boolean cascade, String deleteReason);

    /**
     * 激活流程实例
     */
    void activate(String processInstanceId);

    /**
     * 挂起流程实例
     */
    void suspend(String processInstanceId);

    /**
     * 查询我的流程汇总信息
     */
    List<Object> listMyInvolvedSummary(ProcessInstanceQueryVo processInstanceQueryVo);

    /**
     * 根据流程实例ID获取任务信息
     *
     * @param processInstanceId 实例ID
     */
    String getTask(String processInstanceId);
}
