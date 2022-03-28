package org.dshubs.odc.workflow.service;

import org.dshubs.odc.workflow.entity.IdentityRequest;
import org.dshubs.odc.workflow.entity.ProcessDefinitionRequest;
import org.flowable.engine.repository.ProcessDefinition;

import javax.servlet.http.HttpServletRequest;


/**
 * Flowable 流程定义Service
 *
 * @author 湖南牛数商智信息科技有限公司
 */
public interface ProcessDefinitionService {

    /**
     * 查询单一流程定义
     *
     * @param processDefinitionId
     * @return
     */
    ProcessDefinition getProcessDefinitionById(String processDefinitionId);

    /**
     * 删除流程定义
     *
     * @param processDefinitionId
     * @param cascade
     */
    void delete(String processDefinitionId, Boolean cascade);

    /**
     * 激活流程定义
     *
     * @param actionRequest
     */
    void activate(ProcessDefinitionRequest actionRequest);

    /**
     * 挂起流程定义
     *
     * @param actionRequest
     */
    void suspend(ProcessDefinitionRequest actionRequest);

    /**
     * 导入流程定义
     *
     * @param tenantId
     * @param request
     */
    void doImport(String tenantId, HttpServletRequest request);

    /**
     * 保存流程授权
     *
     * @param identityRequest
     */
    void saveProcessDefinitionIdentityLink(IdentityRequest identityRequest);

    /**
     * 删除流程授权
     *
     * @param processDefinitionId
     * @param identityId
     * @param type
     */
    void deleteProcessDefinitionIdentityLink(String processDefinitionId, String identityId, String type);
}
