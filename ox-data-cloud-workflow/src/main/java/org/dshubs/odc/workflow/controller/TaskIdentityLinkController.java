package org.dshubs.odc.workflow.controller;

import io.swagger.annotations.ApiOperation;
import org.dshubs.odc.workflow.common.AjaxResult;
import org.dshubs.odc.workflow.common.BaseFlowableController;
import org.dshubs.odc.workflow.common.annotation.PreAuthorize;
import org.dshubs.odc.workflow.entity.IdentityRequest;
import org.dshubs.odc.workflow.service.FlowableTaskService;
import org.flowable.identitylink.api.history.HistoricIdentityLink;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务关联
 *
 * @author 湖南牛数商智信息科技有限公司
 */
@RestController
@RequestMapping("/flowable/taskIdentityLink")
public class TaskIdentityLinkController extends BaseFlowableController {

    @Autowired
    protected FlowableTaskService flowableTaskService;


    @PreAuthorize(hasPermi = "flowable:taskIdentityLink:list")
    @GetMapping(value = "/list")
    public AjaxResult list(@RequestParam String taskId) {
        HistoricTaskInstance task = flowableTaskService.getHistoricTaskInstanceNotNull(taskId);
        List<HistoricIdentityLink> historicIdentityLinks = historyService.getHistoricIdentityLinksForTask(task.getId());
        return AjaxResult.success(responseFactory.createTaskIdentityResponseList(historicIdentityLinks));

    }

    @ApiOperation(value = "新增任务授权")
    @PreAuthorize(hasPermi = "flowable:taskIdentityLink:save")
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody IdentityRequest taskIdentityRequest) {
        flowableTaskService.saveTaskIdentityLink(taskIdentityRequest);
        return AjaxResult.success();
    }

    @ApiOperation(value = "删除任务授权")
    @PreAuthorize(hasPermi = "flowable:taskIdentityLink:deleteIdentityLink")
    @DeleteMapping(value = "/delete")
    public AjaxResult deleteIdentityLink(@RequestParam String taskId, @RequestParam String identityId,
                                         @RequestParam String identityType) {
        flowableTaskService.deleteTaskIdentityLink(taskId, identityId, identityType);
        return AjaxResult.success();
    }
}
