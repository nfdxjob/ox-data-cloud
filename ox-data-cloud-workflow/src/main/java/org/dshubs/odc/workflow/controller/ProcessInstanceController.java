package org.dshubs.odc.workflow.controller;

import com.alibaba.nacos.common.utils.CollectionUtils;
import io.swagger.annotations.ApiOperation;
import org.dshubs.odc.workflow.common.AjaxResult;
import org.dshubs.odc.workflow.common.BaseFlowableController;
import org.dshubs.odc.workflow.common.FlowablePage;
import org.dshubs.odc.workflow.common.annotation.PreAuthorize;
import org.dshubs.odc.workflow.common.constant.FlowableConstant;
import org.dshubs.odc.workflow.common.utils.CommonUtil;
import org.dshubs.odc.workflow.common.utils.ObjectUtils;
import org.dshubs.odc.workflow.common.utils.SecurityUtils;
import org.dshubs.odc.workflow.entity.ProcessInstanceDetailResponse;
import org.dshubs.odc.workflow.entity.ProcessInstanceRequest;
import org.dshubs.odc.workflow.entity.query.ProcessInstanceQueryVo;
import org.dshubs.odc.workflow.service.ProcessInstanceService;
import org.dshubs.odc.workflow.wrapper.CommentListWrapper;
import org.dshubs.odc.workflow.wrapper.ProcInsListWrapper;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.UserTask;
import org.flowable.common.engine.api.query.QueryProperty;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.history.HistoricProcessInstanceQuery;
import org.flowable.engine.impl.HistoricProcessInstanceQueryProperty;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Comment;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 流程实例Controller
 *
 * @author 湖南牛数商智信息科技有限公司
 */
@RestController
@RequestMapping("/flowable/processInstance")
public class ProcessInstanceController extends BaseFlowableController {

    private static final Map<String, QueryProperty> allowedSortProperties = new HashMap<>();

    @Autowired
    ProcessInstanceService processInstanceService;

    static {
        allowedSortProperties.put(FlowableConstant.ID, HistoricProcessInstanceQueryProperty.PROCESS_INSTANCE_ID_);
        allowedSortProperties.put(FlowableConstant.PROCESS_DEFINITION_ID,
                HistoricProcessInstanceQueryProperty.PROCESS_DEFINITION_ID);
        allowedSortProperties.put(FlowableConstant.PROCESS_DEFINITION_KEY,
                HistoricProcessInstanceQueryProperty.PROCESS_DEFINITION_KEY);
        allowedSortProperties.put(FlowableConstant.BUSINESS_KEY, HistoricProcessInstanceQueryProperty.BUSINESS_KEY);
        allowedSortProperties.put("startTime", HistoricProcessInstanceQueryProperty.START_TIME);
        allowedSortProperties.put("endTime", HistoricProcessInstanceQueryProperty.END_TIME);
        allowedSortProperties.put("duration", HistoricProcessInstanceQueryProperty.DURATION);
        allowedSortProperties.put(FlowableConstant.TENANT_ID, HistoricProcessInstanceQueryProperty.TENANT_ID);
    }

    @PreAuthorize(hasPermi = "flowable:processInstance:list")
    @GetMapping(value = "/list")
    public AjaxResult list(ProcessInstanceQueryVo processInstanceQueryVo) {
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();

        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getProcessDefinitionCategory())) {
            query.processDefinitionCategory(processInstanceQueryVo.getProcessDefinitionCategory());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getProcessInstanceId())) {
            query.processInstanceId(processInstanceQueryVo.getProcessInstanceId());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getProcessInstanceName())) {
            query.processInstanceNameLike(processInstanceQueryVo.getProcessInstanceName());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getProcessDefinitionName())) {
            query.processDefinitionName(processInstanceQueryVo.getProcessDefinitionName());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getProcessDefinitionKey())) {
            query.processDefinitionKey(processInstanceQueryVo.getProcessDefinitionKey());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getProcessDefinitionId())) {
            query.processDefinitionId(processInstanceQueryVo.getProcessDefinitionId());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getBusinessKey())) {
            query.processInstanceBusinessKey(processInstanceQueryVo.getBusinessKey());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getInvolvedUser())) {
            query.involvedUser(processInstanceQueryVo.getInvolvedUser());
        }
        if (!processInstanceQueryVo.getFinished().equals(processInstanceQueryVo.getUnfinished())) {
            if (processInstanceQueryVo.getFinished()) {
                query.finished();
            }
            if (processInstanceQueryVo.getUnfinished()) {
                query.unfinished();
            }
        }

        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getSuperProcessInstanceId())) {
            query.superProcessInstanceId(processInstanceQueryVo.getSuperProcessInstanceId());
        }
        if (processInstanceQueryVo.getExcludeSubprocesses()) {
            query.excludeSubprocesses(processInstanceQueryVo.getExcludeSubprocesses());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getFinishedAfter())) {
            query.finishedAfter(ObjectUtils.convertToDatetime(processInstanceQueryVo.getFinishedAfter()));
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getFinishedBefore())) {
            query.finishedBefore(ObjectUtils.convertToDatetime(processInstanceQueryVo.getFinishedBefore()));
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getStartedAfter())) {
            query.startedAfter(ObjectUtils.convertToDatetime(processInstanceQueryVo.getStartedAfter()));
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getStartedBefore())) {
            query.startedBefore(ObjectUtils.convertToDatetime(processInstanceQueryVo.getStartedBefore()));
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getStartedBy())) {
            query.startedBy(processInstanceQueryVo.getStartedBy());
        }
        // startByMe 覆盖 startedBy
        if (processInstanceQueryVo.getStartedByMe()) {
            query.startedBy(Objects.requireNonNull(SecurityUtils.getUserId()).toString());
        }
        // ccToMe 抄送我
        if (processInstanceQueryVo.getCcToMe()) {
            query.involvedUser(Objects.requireNonNull(SecurityUtils.getUserId()).toString(), FlowableConstant.CC);
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getTenantId())) {
            query.processInstanceTenantIdLike(processInstanceQueryVo.getTenantId());
        }
        FlowablePage page = this.pageList(processInstanceQueryVo, query, ProcInsListWrapper.class, allowedSortProperties,
                HistoricProcessInstanceQueryProperty.START_TIME);
        return AjaxResult.success(page);
    }

    @GetMapping(value = "/listAll")
    public AjaxResult listAll(@RequestBody ProcessInstanceQueryVo processInstanceQueryVo) {
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();

        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getProcessDefinitionCategory())) {
            query.processDefinitionCategory(processInstanceQueryVo.getProcessDefinitionCategory());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getProcessInstanceId())) {
            query.processInstanceId(processInstanceQueryVo.getProcessInstanceId());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getProcessInstanceName())) {
            query.processInstanceNameLike(processInstanceQueryVo.getProcessInstanceName());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getProcessDefinitionName())) {
            query.processDefinitionName(processInstanceQueryVo.getProcessDefinitionName());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getProcessDefinitionKey())) {
            query.processDefinitionKey(processInstanceQueryVo.getProcessDefinitionKey());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getProcessDefinitionId())) {
            query.processDefinitionId(processInstanceQueryVo.getProcessDefinitionId());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getBusinessKey())) {
            query.processInstanceBusinessKey(processInstanceQueryVo.getBusinessKey());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getInvolvedUser())) {
            query.involvedUser(processInstanceQueryVo.getInvolvedUser());
        }
        if (!processInstanceQueryVo.getFinished().equals(processInstanceQueryVo.getUnfinished())) {
            if (processInstanceQueryVo.getFinished()) {
                query.finished();
            }
            if (processInstanceQueryVo.getUnfinished()) {
                query.unfinished();
            }
        }

        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getSuperProcessInstanceId())) {
            query.superProcessInstanceId(processInstanceQueryVo.getSuperProcessInstanceId());
        }
        if (processInstanceQueryVo.getExcludeSubprocesses()) {
            query.excludeSubprocesses(processInstanceQueryVo.getExcludeSubprocesses());
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getFinishedAfter())) {
            query.finishedAfter(ObjectUtils.convertToDatetime(processInstanceQueryVo.getFinishedAfter()));
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getFinishedBefore())) {
            query.finishedBefore(ObjectUtils.convertToDatetime(processInstanceQueryVo.getFinishedBefore()));
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getStartedAfter())) {
            query.startedAfter(ObjectUtils.convertToDatetime(processInstanceQueryVo.getStartedAfter()));
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getStartedBefore())) {
            query.startedBefore(ObjectUtils.convertToDatetime(processInstanceQueryVo.getStartedBefore()));
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getStartedBy())) {
            query.startedBy(processInstanceQueryVo.getStartedBy());
        }
        // startByMe 覆盖 startedBy
        if (processInstanceQueryVo.getStartedByMe()) {
            query.startedBy(Objects.requireNonNull(SecurityUtils.getUserId()).toString());
        }
        // ccToMe 抄送我
        if (processInstanceQueryVo.getCcToMe()) {
            query.involvedUser(Objects.requireNonNull(SecurityUtils.getUserId()).toString(), FlowableConstant.CC);
        }
        if (CommonUtil.isNotEmptyAfterTrim(processInstanceQueryVo.getTenantId())) {
            query.processInstanceTenantIdLike(processInstanceQueryVo.getTenantId());
        }

        FlowablePage page = this.pageList(processInstanceQueryVo, query, ProcInsListWrapper.class, allowedSortProperties,
                HistoricProcessInstanceQueryProperty.START_TIME);
        return AjaxResult.success(page);
    }

    @GetMapping(value = "/listMyInvolvedSummary")
    public AjaxResult listMyInvolvedSummary(ProcessInstanceQueryVo processInstanceQueryVo) {
        processInstanceQueryVo.setUserId(Objects.requireNonNull(SecurityUtils.getUserId()).toString());
        return AjaxResult.success(this.processInstanceService.listMyInvolvedSummary(processInstanceQueryVo));
    }

    @GetMapping(value = "/listMyInvolved")
    public AjaxResult listMyInvolved(ProcessInstanceQueryVo processInstanceQueryVo) {
        processInstanceQueryVo.setInvolvedUser(Objects.requireNonNull(SecurityUtils.getUserId()).toString());
        return list(processInstanceQueryVo);
    }

    @GetMapping(value = "/listStartedByMe")
    public AjaxResult listStartedByMe(ProcessInstanceQueryVo processInstanceQueryVo) {
        processInstanceQueryVo.setStartedByMe(true);
        return list(processInstanceQueryVo);
    }

    @GetMapping(value = "/listCcToMe")
    public AjaxResult listCcToMe(ProcessInstanceQueryVo processInstanceQueryVo) {
        processInstanceQueryVo.setCcToMe(true);
        return list(processInstanceQueryVo);
    }

    @GetMapping(value = "/queryById")
    public AjaxResult queryById(@RequestParam String processInstanceId) {
        permissionService.validateReadPermissionOnProcessInstance(Objects.requireNonNull(SecurityUtils.getUserId()).toString(), processInstanceId);
        ProcessInstance processInstance = null;
        HistoricProcessInstance historicProcessInstance =
                processInstanceService.getHistoricProcessInstanceById(processInstanceId);
        if (historicProcessInstance.getEndTime() == null) {
            processInstance = processInstanceService.getProcessInstanceById(processInstanceId);
        }
        ProcessInstanceDetailResponse pidr =
                responseFactory.createProcessInstanceDetailResponse(historicProcessInstance, processInstance);
        return AjaxResult.success(pidr);
    }

    @ApiOperation(value = "启动流程实例")
    @PostMapping(value = "/start")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult start(@RequestBody ProcessInstanceRequest processInstanceRequest) {
        String processInstanceId = processInstanceService.start(processInstanceRequest);
        return AjaxResult.success(processInstanceId);
    }

    @ApiOperation(value = "删除流程实例")
    @PreAuthorize(hasPermi = "flowable:processInstance:delete")
    @DeleteMapping(value = "/delete")
    public AjaxResult delete(@RequestParam String processInstanceId, @RequestParam(required = false) boolean cascade,
                             @RequestParam(required = false) String deleteReason) {
        processInstanceService.delete(processInstanceId, cascade, deleteReason);
        return AjaxResult.success();
    }

    @ApiOperation(value = "挂起流程实例")
    @PreAuthorize(hasPermi = "flowable:processInstance:suspend")
    @PutMapping(value = "/suspend")
    public AjaxResult suspend(@RequestBody ProcessInstanceRequest processInstanceRequest) {
        processInstanceService.suspend(processInstanceRequest.getProcessInstanceId());
        return AjaxResult.success();
    }

    @ApiOperation(value = "激活流程实例")
    @PreAuthorize(hasPermi = "flowable:processInstance:activate")
    @PutMapping(value = "/activate")
    public AjaxResult activate(@RequestBody ProcessInstanceRequest processInstanceRequest) {
        processInstanceService.activate(processInstanceRequest.getProcessInstanceId());
        return AjaxResult.success();
    }

    @GetMapping(value = "/comments")
    public AjaxResult comments(@RequestParam String processInstanceId) {
        permissionService.validateReadPermissionOnProcessInstance(Objects.requireNonNull(SecurityUtils.getUserId()).toString(), processInstanceId);
        List<Comment> datas = taskService.getProcessInstanceComments(processInstanceId);
        Collections.reverse(datas);
        return AjaxResult.success(this.listWrapper(CommentListWrapper.class, datas));
    }

    @GetMapping(value = "/formData")
    public AjaxResult formData(@RequestParam String processInstanceId) {
        HistoricProcessInstance processInstance =
                permissionService.validateReadPermissionOnProcessInstance(Objects.requireNonNull(SecurityUtils.getUserId()).toString(), processInstanceId);
        Object renderedStartForm = formService.getRenderedStartForm(processInstance.getProcessDefinitionId());
        Map<String, Object> variables;
        if (processInstance.getEndTime() == null) {
            variables = runtimeService.getVariables(processInstanceId);
        } else {
            List<HistoricVariableInstance> hisVals =
                    historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
            variables = new HashMap<>(16);
            for (HistoricVariableInstance variableInstance : hisVals) {
                variables.put(variableInstance.getVariableName(), variableInstance.getValue());
            }
        }
        Map<String, Object> ret = new HashMap<>(4);
        boolean showBusinessKey = isShowBusinessKey(processInstance.getProcessDefinitionId());
        ret.put("showBusinessKey", showBusinessKey);
        ret.put(FlowableConstant.BUSINESS_KEY, processInstance.getBusinessKey());
        ret.put("renderedStartForm", renderedStartForm);
        ret.put("variables", variables);
        return AjaxResult.success(ret);
    }

    @GetMapping("/processInstanceId")
    public AjaxResult getTask(@RequestParam String processInstanceId) {
        return AjaxResult.success(processInstanceService.getTask(processInstanceId));
    }


    /**
     * 获取节点列表
     *
     * @param processInstanceId 流程实例ID
     * @return AjaxResult
     */
    @GetMapping("listNodes/{processInstanceId}")
    public AjaxResult list(@PathVariable String processInstanceId) {
        List<FlowElement> list = new ArrayList<>();
        //获取流程发布Id信息
        String definitionId = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getProcessDefinitionId();        //获取所有节点信息
        List<org.flowable.bpmn.model.Process> processes = repositoryService.getBpmnModel(definitionId).getProcesses();
        System.out.println("processes size:" + processes.size());
        for (org.flowable.bpmn.model.Process process : processes) {
            Collection<FlowElement> flowElements = process.getFlowElements();
            if (CollectionUtils.isNotEmpty(flowElements)) {
                for (FlowElement flowElement : flowElements) {
                    if (flowElement instanceof UserTask) {
                        list.add(flowElement);
                    }
                }
            }
        }
        return AjaxResult.success(list);
    }

}
