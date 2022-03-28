package org.dshubs.odc.workflow.controller;

import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.workflow.common.AjaxResult;
import org.dshubs.odc.workflow.common.BaseFlowableController;
import org.dshubs.odc.workflow.common.FlowablePage;
import org.dshubs.odc.workflow.common.annotation.PreAuthorize;
import org.dshubs.odc.workflow.common.constant.FlowableConstant;
import org.dshubs.odc.workflow.common.utils.CommonUtil;
import org.dshubs.odc.workflow.common.utils.ObjectUtils;
import org.dshubs.odc.workflow.common.utils.SecurityUtils;
import org.dshubs.odc.workflow.entity.ActReProcdef;
import org.dshubs.odc.workflow.entity.ProcessDefinitionRequest;
import org.dshubs.odc.workflow.entity.ProcessDefinitionResponse;
import org.dshubs.odc.workflow.entity.query.ProcessDefinitionQueryVo;
import org.dshubs.odc.workflow.service.IActReProcdefService;
import org.dshubs.odc.workflow.service.ProcessDefinitionService;
import org.dshubs.odc.workflow.wrapper.ProcDefListWrapper;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.query.QueryProperty;
import org.flowable.engine.impl.ProcessDefinitionQueryProperty;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 流程定义Controller
 *
 * @author 湖南牛数商智信息科技有限公司
 */
@RestController
@RequestMapping("/flowable/processDefinition")
@Slf4j
public class ProcessDefinitionController extends BaseFlowableController {

    private static final Map<String, QueryProperty> ALLOWED_SORT_PROPERTIES = new HashMap<>();

    @Autowired
    private ProcessDefinitionService processDefinitionService;

    @Autowired
    private IActReProcdefService reProcdefService;

    static {
        ALLOWED_SORT_PROPERTIES.put(FlowableConstant.ID, ProcessDefinitionQueryProperty.PROCESS_DEFINITION_ID);
        ALLOWED_SORT_PROPERTIES.put(FlowableConstant.KEY, ProcessDefinitionQueryProperty.PROCESS_DEFINITION_KEY);
        ALLOWED_SORT_PROPERTIES.put(FlowableConstant.CATEGORY,
                ProcessDefinitionQueryProperty.PROCESS_DEFINITION_CATEGORY);
        ALLOWED_SORT_PROPERTIES.put(FlowableConstant.NAME, ProcessDefinitionQueryProperty.PROCESS_DEFINITION_NAME);
        ALLOWED_SORT_PROPERTIES.put(FlowableConstant.VERSION,
                ProcessDefinitionQueryProperty.PROCESS_DEFINITION_VERSION);
        ALLOWED_SORT_PROPERTIES.put(FlowableConstant.TENANT_ID,
                ProcessDefinitionQueryProperty.PROCESS_DEFINITION_TENANT_ID);
    }

    @PreAuthorize(hasPermi = "flowable:processDefinition:list")
    @GetMapping(value = "/list")
    public AjaxResult list(ProcessDefinitionQueryVo processDefinitionQueryVo) {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        if (ObjectUtils.isNotEmpty(processDefinitionQueryVo.getProcessDefinitionId())) {
            processDefinitionQuery.processDefinitionId(processDefinitionQueryVo.getProcessDefinitionId());
        }
        if (ObjectUtils.isNotEmpty(processDefinitionQueryVo.getProcessDefinitionCategory())) {
            processDefinitionQuery.processDefinitionCategoryLike(ObjectUtils.convertToLike(processDefinitionQueryVo.getProcessDefinitionCategory()));
        }
        if (ObjectUtils.isNotEmpty(processDefinitionQueryVo.getProcessDefinitionKey())) {
            processDefinitionQuery.processDefinitionKeyLike(ObjectUtils.convertToLike(processDefinitionQueryVo.getProcessDefinitionKey()));
        }
        if (ObjectUtils.isNotEmpty(processDefinitionQueryVo.getProcessDefinitionName())) {
            processDefinitionQuery.processDefinitionNameLike(ObjectUtils.convertToLike(processDefinitionQueryVo.getProcessDefinitionName()));
        }
        if (ObjectUtils.isNotEmpty(processDefinitionQueryVo.getProcessDefinitionVersion())) {
            processDefinitionQuery.processDefinitionVersion(processDefinitionQueryVo.getProcessDefinitionVersion());
        }


        Boolean suspended = CommonUtil.isEmptyDefault(processDefinitionQueryVo.getSuspended(), false);
        Boolean active = CommonUtil.isEmptyDefault(processDefinitionQueryVo.getActive(), false);
        if (!suspended.equals(active)) {
            if (suspended) {
                processDefinitionQuery.suspended();
            }
            if (active) {
                processDefinitionQuery.active();
            }
        }
        if (processDefinitionQueryVo.getLatestVersion()) {
            processDefinitionQuery.latestVersion();
        }
        if (ObjectUtils.isNotEmpty(processDefinitionQueryVo.getStartableByUser())) {
            processDefinitionQuery.startableByUser(processDefinitionQueryVo.getStartableByUser());
        }
        if (ObjectUtils.isNotEmpty(processDefinitionQueryVo.getTenantId())) {
            processDefinitionQuery.processDefinitionTenantId(processDefinitionQueryVo.getTenantId());
        }
        FlowablePage page = this.pageList(processDefinitionQueryVo, processDefinitionQuery, ProcDefListWrapper.class,
                ALLOWED_SORT_PROPERTIES);
        return AjaxResult.success(page);
    }

    @GetMapping(value = "/listMyself")
    public AjaxResult listMyself(ProcessDefinitionQueryVo processDefinitionQueryVo) {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        if (ObjectUtils.isNotEmpty(processDefinitionQueryVo.getProcessDefinitionName())) {
            processDefinitionQuery.processDefinitionNameLike(ObjectUtils.convertToLike(processDefinitionQueryVo.getProcessDefinitionName()));
        }
        if (ObjectUtils.isNotEmpty(processDefinitionQueryVo.getProcessDefinitionKey())) {
            processDefinitionQuery.processDefinitionKey(processDefinitionQueryVo.getProcessDefinitionKey());
        }
        if (StringUtils.isNotBlank(processDefinitionQueryVo.getIsMe())) {
            processDefinitionQuery.latestVersion().active();
        } else {
            processDefinitionQuery.latestVersion().active().startableByUser(Objects.requireNonNull(SecurityUtils.getUserId()).toString());
        }
        FlowablePage page = this.pageList(processDefinitionQueryVo, processDefinitionQuery, ProcDefListWrapper.class,
                ALLOWED_SORT_PROPERTIES);
        return AjaxResult.success(page);
    }

    @GetMapping(value = "/queryById")
    public AjaxResult queryById(@RequestParam String processDefinitionId) {
        permissionService.validateReadPermissionOnProcessDefinition(Objects.requireNonNull(SecurityUtils.getUserId()).toString(), processDefinitionId);
        ProcessDefinition processDefinition = processDefinitionService.getProcessDefinitionById(processDefinitionId);
        String formKey = null;
        if (processDefinition.hasStartFormKey()) {
            formKey = formService.getStartFormKey(processDefinitionId);
        }
        ProcessDefinitionResponse processDefinitionResponse =
                responseFactory.createProcessDefinitionResponse(processDefinition, formKey);
        return AjaxResult.success(processDefinitionResponse);
    }

    @GetMapping(value = "/renderedStartForm")
    public AjaxResult renderedStartForm(@RequestParam String processDefinitionId) {
        permissionService.validateReadPermissionOnProcessDefinition(Objects.requireNonNull(SecurityUtils.getUserId()).toString(), processDefinitionId);
        Object renderedStartForm = formService.getRenderedStartForm(processDefinitionId);
        boolean showBusinessKey = this.isShowBusinessKey(processDefinitionId);
        return AjaxResult.success(ImmutableMap.of("renderedStartForm", renderedStartForm != null ? renderedStartForm : "",
                "showBusinessKey", showBusinessKey));
    }

    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> image(@RequestParam String processDefinitionId, @RequestParam(required = false) String userId) {
        if (StringUtils.isNotBlank(userId)) {
            permissionService.validateReadPermissionOnProcessDefinition(userId, processDefinitionId);
        } else {
            permissionService.validateReadPermissionOnProcessDefinition(Objects.requireNonNull(SecurityUtils.getUserId()).toString(), processDefinitionId);
        }
        ProcessDefinition processDefinition = processDefinitionService.getProcessDefinitionById(processDefinitionId);
        InputStream imageStream = repositoryService.getProcessDiagram(processDefinition.getId());
        if (imageStream == null) {
            throw new FlowableException(messageFormat("Process definition image is not found with id {0}",
                    processDefinitionId));
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.IMAGE_PNG);
        try {
            return new ResponseEntity<>(IOUtils.toByteArray(imageStream), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            throw new FlowableException(messageFormat("Process definition image read error with id {0}",
                    processDefinitionId), e);
        }
    }

    @PreAuthorize(hasPermi = "flowable:processDefinition:xml")
    @GetMapping(value = "/xml")
    public ResponseEntity<byte[]> xml(@RequestParam String processDefinitionId) {
        permissionService.validateReadPermissionOnProcessDefinition(Objects.requireNonNull(SecurityUtils.getUserId()).toString(), processDefinitionId);
        ProcessDefinition processDefinition = processDefinitionService.getProcessDefinitionById(processDefinitionId);
        String deploymentId = processDefinition.getDeploymentId();
        String resourceId = processDefinition.getResourceName();
        if (deploymentId == null || deploymentId.length() == 0) {
            throw new FlowableException(messageFormat("Process definition deployment id is not found with id {0}",
                    processDefinitionId));
        }
        if (resourceId == null || resourceId.length() == 0) {
            throw new FlowableException(messageFormat("Process definition resource id is not found with id {0}",
                    processDefinitionId));
        }
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
        if (deployment == null) {
            throw new FlowableException(messageFormat("Process definition deployment is not found with deploymentId " + "{0}", deploymentId));
        }

        List<String> resourceList = repositoryService.getDeploymentResourceNames(deploymentId);
        if (ObjectUtils.isEmpty(resourceList) || !resourceList.contains(resourceId)) {
            throw new FlowableException(messageFormat("Process definition resourceId {0} is not found with " +
                    "deploymentId {1}", resourceId, deploymentId));
        }
        InputStream resourceStream = repositoryService.getResourceAsStream(deploymentId, resourceId);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_XML);
        try {
            return new ResponseEntity<>(IOUtils.toByteArray(resourceStream), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取流程定义XML信息异常", e);
            throw new FlowableException(messageFormat("ProcessDefinition xml read error with id {0}", deploymentId), e);
        }
    }

    @ApiOperation(value = "删除流程定义")
    @PreAuthorize(hasPermi = "flowable:processDefinition:delete")
    @DeleteMapping(value = "/delete")
    public AjaxResult delete(@RequestParam String processDefinitionId, @RequestParam(required = false, defaultValue =
            "false") Boolean cascade) {
        processDefinitionService.delete(processDefinitionId, cascade);
        return AjaxResult.success();
    }

    @ApiOperation(value = "激活流程定义")
    @PreAuthorize(hasPermi = "flowable:processDefinition:activate")
    @PutMapping(value = "/activate")
    public AjaxResult activate(@RequestBody ProcessDefinitionRequest actionRequest) {
        processDefinitionService.activate(actionRequest);
        return AjaxResult.success();
    }

    @ApiOperation(value = "挂起流程定义")
    @PreAuthorize(hasPermi = "flowable:processDefinition:suspend")
    @PutMapping(value = "/suspend")
    public AjaxResult suspend(@RequestBody ProcessDefinitionRequest actionRequest) {
        processDefinitionService.suspend(actionRequest);
        return AjaxResult.success();
    }

    /**
     * 333
     *
     * @param tenantId 22
     * @param request  33
     * @return 233
     */
    @ApiOperation(value = "导入流程定义")
    @PreAuthorize(hasPermi = "flowable:processDefinition:import")
    @PostMapping(value = "/import")
    public AjaxResult doImport(@RequestParam(required = false) String tenantId, HttpServletRequest request) {
        processDefinitionService.doImport(tenantId, request);
        return AjaxResult.success();
    }


    @ApiModelProperty(value = "根据机构ID查询流程")
    @GetMapping("/flowlist/tenantId/{tenantId}")
    public List<ActReProcdef> flowListByTenantId(@PathVariable("tenantId") String tenantId) {
        return reProcdefService.selectByTenantId(tenantId);
    }

}
