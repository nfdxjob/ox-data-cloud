package org.dshubs.odc.workflow.service;

import org.dshubs.odc.workflow.entity.ProcessInstanceRequest;
import org.dshubs.odc.workflow.entity.query.ProcessInstanceQueryVo;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.List;

/**
 * @Description： Flowable 流程实例Service
 * @GithubAuthor : zhanglinfu2012
 * @Date: 2022-03-15 22:05
 * @Version: 1.0.0
 * @Copyright: 湖南牛数商智信息科技有限公司
 */
public interface ProcessInstanceService {
    /**
     * 查询单一流程实例
     *
     * @param processInstanceId
     * @return
     */
    ProcessInstance getProcessInstanceById(String processInstanceId);

    /**
     * 查询单一历史流程实例
     *
     * @param processInstanceId
     * @return
     */
    HistoricProcessInstance getHistoricProcessInstanceById(String processInstanceId);

    /**
     * 启动流程实例
     *
     * @param processInstanceRequest
     */
    String start(ProcessInstanceRequest processInstanceRequest);

    /**
     * 删除流程实例
     *
     * @param processInstanceId
     * @param cascade
     * @param deleteReason
     */
    void delete(String processInstanceId, boolean cascade, String deleteReason);

    /**
     * 激活流程实例
     *
     * @param processInstanceId
     */
    void activate(String processInstanceId);

    /**
     * 挂起流程实例
     *
     * @param processInstanceId
     */
    void suspend(String processInstanceId);

    /**
     * 查询我的流程汇总信息
     *
     * @param processInstanceQueryVo
     */
    List listMyInvolvedSummary(ProcessInstanceQueryVo processInstanceQueryVo);

    String getTask(String processInstanceId);
}
