package org.dshubs.odc.workflow.controller;

import io.swagger.annotations.ApiOperation;
import org.dshubs.odc.workflow.common.AjaxResult;
import org.dshubs.odc.workflow.common.BaseFlowableController;
import org.dshubs.odc.workflow.common.annotation.PreAuthorize;
import org.dshubs.odc.workflow.entity.IdentityRequest;
import org.dshubs.odc.workflow.service.ProcessDefinitionService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.identitylink.api.IdentityLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程定义关联
 *
 * @author 湖南牛数商智信息科技有限公司
 */
@RestController
@RequestMapping("/flowable/processDefinitionIdentityLink")
public class ProcessDefinitionIdentityLinkController extends BaseFlowableController {

    @Autowired
    private ProcessDefinitionService processDefinitionService;

    @PreAuthorize(hasPermi = "flowable:processDefinitionIdentityLink:list")
    @GetMapping(value = "/list")
    public AjaxResult list(@RequestParam String processDefinitionId) {
        ProcessDefinition processDefinition = processDefinitionService.getProcessDefinitionById(processDefinitionId);
        List<IdentityLink> identityLinks =
                repositoryService.getIdentityLinksForProcessDefinition(processDefinition.getId());
        return AjaxResult.success(responseFactory.createIdentityResponseList(identityLinks));
    }

    @ApiOperation(value = "新增流程定义授权")
    @PreAuthorize(hasPermi = "flowable:processDefinitionIdentityLink:save")
    @PostMapping(value = "/save")
    public AjaxResult save(@RequestBody IdentityRequest identityRequest) {
        processDefinitionService.saveProcessDefinitionIdentityLink(identityRequest);
        return AjaxResult.success();
    }

    @ApiOperation(value = "删除流程定义授权")
    @PreAuthorize(hasPermi = "flowable:processDefinitionIdentityLink:delete")
    @DeleteMapping(value = "/delete")
    public AjaxResult delete(@RequestParam String processDefinitionId, @RequestParam String identityId,
                             @RequestParam String identityType) {
        processDefinitionService.deleteProcessDefinitionIdentityLink(processDefinitionId, identityId, identityType);
        return AjaxResult.success();
    }
}
