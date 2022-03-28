package org.dshubs.odc.workflow.controller;

import io.swagger.annotations.ApiOperation;
import org.dshubs.odc.workflow.common.AjaxResult;
import org.dshubs.odc.workflow.common.BaseFlowableController;
import org.dshubs.odc.workflow.common.annotation.PreAuthorize;
import org.flowable.job.api.Job;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程定义定时任务
 *
 * @author 湖南牛数商智信息科技有限公司
 */
@RestController
@RequestMapping("/flowable/processDefinitionJob")
public class ProcessDefinitionJobController extends BaseFlowableController {

    @PreAuthorize(hasPermi = "flowable:processDefinitionJob:list")
    @GetMapping(value = "/list")
    public List<Job> list(@RequestParam String processDefinitionId) {
        return managementService.createTimerJobQuery().processDefinitionId(processDefinitionId).list();
    }

    @ApiOperation(value = "新增流程定义定时任务")
    @PreAuthorize(hasPermi = "flowable:processDefinitionJob:delete")
    @DeleteMapping(value = "/delete")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult deleteJob(@RequestParam String jobId) {
        managementService.deleteTimerJob(jobId);
        return AjaxResult.success();
    }
}
