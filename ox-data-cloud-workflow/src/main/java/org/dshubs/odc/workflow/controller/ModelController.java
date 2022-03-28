package org.dshubs.odc.workflow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.dshubs.odc.workflow.common.AjaxResult;
import org.dshubs.odc.workflow.common.BaseFlowableController;
import org.dshubs.odc.workflow.common.FlowablePage;
import org.dshubs.odc.workflow.common.ResponseFactory;
import org.dshubs.odc.workflow.common.annotation.PreAuthorize;
import org.dshubs.odc.workflow.common.cmd.DeployModelCmd;
import org.dshubs.odc.workflow.common.cmd.SaveModelEditorCmd;
import org.dshubs.odc.workflow.common.constant.FlowableConstant;
import org.dshubs.odc.workflow.common.utils.ObjectUtils;
import org.dshubs.odc.workflow.common.utils.SecurityUtils;
import org.dshubs.odc.workflow.entity.ModelRequest;
import org.dshubs.odc.workflow.entity.ModelResponse;
import org.dshubs.odc.workflow.entity.query.ModelQueryVo;
import org.dshubs.odc.workflow.wrapper.ModelListWrapper;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.common.engine.api.query.QueryProperty;
import org.flowable.common.engine.impl.util.IoUtil;
import org.flowable.engine.impl.ModelQueryProperty;
import org.flowable.engine.repository.Model;
import org.flowable.engine.repository.ModelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 表单模型Controller
 *
 * @author 湖南牛数商智信息科技有限公司
 */
@RestController
@RequestMapping("/flowable/model")
public class ModelController extends BaseFlowableController {

    @Autowired
    protected ResponseFactory responseFactory;

    @Autowired
    protected ObjectMapper objectMapper;

    private static final Map<String, QueryProperty> ALLOWED_SORT_PROPERTIES = new HashMap<>();

    static {
        ALLOWED_SORT_PROPERTIES.put("id", ModelQueryProperty.MODEL_ID);
        ALLOWED_SORT_PROPERTIES.put("category", ModelQueryProperty.MODEL_CATEGORY);
        ALLOWED_SORT_PROPERTIES.put("createTime", ModelQueryProperty.MODEL_CREATE_TIME);
        ALLOWED_SORT_PROPERTIES.put("key", ModelQueryProperty.MODEL_KEY);
        ALLOWED_SORT_PROPERTIES.put("lastUpdateTime", ModelQueryProperty.MODEL_LAST_UPDATE_TIME);
        ALLOWED_SORT_PROPERTIES.put("name", ModelQueryProperty.MODEL_NAME);
        ALLOWED_SORT_PROPERTIES.put("version", ModelQueryProperty.MODEL_VERSION);
        ALLOWED_SORT_PROPERTIES.put("tenantId", ModelQueryProperty.MODEL_TENANT_ID);
    }

    @GetMapping(value = "/list")
    public AjaxResult list(ModelQueryVo modelQueryVo) {
        ModelQuery modelQuery = repositoryService.createModelQuery();

        if (ObjectUtils.isNotEmpty(modelQueryVo.getModelId())) {
            modelQuery.modelId(modelQueryVo.getModelId());
        }
        if (ObjectUtils.isNotEmpty(modelQueryVo.getModelCategory())) {
            modelQuery.modelCategoryLike(ObjectUtils.convertToLike(modelQueryVo.getModelCategory()));
        }
        if (ObjectUtils.isNotEmpty(modelQueryVo.getModelName())) {
            modelQuery.modelNameLike(modelQueryVo.getModelName());
        }
        if (ObjectUtils.isNotEmpty(modelQueryVo.getModelKey())) {
            modelQuery.modelKey(modelQueryVo.getModelKey());
        }
        if (ObjectUtils.isNotEmpty(modelQueryVo.getModelVersion())) {
            modelQuery.modelVersion(modelQueryVo.getModelVersion());
        }
        if (modelQueryVo.getLatestVersion() != null) {
            if (modelQueryVo.getLatestVersion()) {
                modelQuery.latestVersion();
            }
        }
        if (ObjectUtils.isNotEmpty(modelQueryVo.getDeploymentId())) {
            modelQuery.deploymentId(modelQueryVo.getDeploymentId());
        }
        if (modelQueryVo.getDeployed() != null) {
            if (modelQueryVo.getDeployed()) {
                modelQuery.deployed();
            } else {
                modelQuery.notDeployed();
            }
        }
        if (ObjectUtils.isNotEmpty(modelQueryVo.getTenantId())) {
            modelQuery.modelTenantId(modelQueryVo.getTenantId());
        }

        modelQuery.orderByLastUpdateTime().desc();
        FlowablePage page = this.pageList(modelQueryVo, modelQuery, ModelListWrapper.class, ALLOWED_SORT_PROPERTIES);
        return AjaxResult.success(page);
    }

    @GetMapping("getuuid")
    @ApiOperation("获取uuid")
    public AjaxResult getuuid() throws Exception {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return AjaxResult.success(uuid);
    }

    @GetMapping(value = "/queryById")
    public AjaxResult queryById(@RequestParam String id) throws UnsupportedEncodingException {
        Model model = getModelById(id);
        ModelResponse modelResponse = responseFactory.createModelResponse(model);
        if (model.hasEditorSource()) {
            byte[] editorBytes = repositoryService.getModelEditorSource(model.getId());
            String editor = new String(editorBytes, StandardCharsets.UTF_8);
            modelResponse.setEditor(editor);
        }
        return AjaxResult.success(modelResponse);
    }

    protected Model getModelById(String modelId) {
        Model model = repositoryService.getModel(modelId);
        if (model == null) {
            throw new FlowableObjectNotFoundException("No model found with id " + modelId);
        }
        return model;
    }

    protected void checkModelKeyExists(String modelKey) {
        long countNum = repositoryService.createModelQuery().modelKey(modelKey).count();
        if (countNum > 0) {
            throw new FlowableObjectNotFoundException("ModelKey already exists with id " + modelKey);
        }
    }

    @ApiOperation(value = "新增流程模型")
    @PreAuthorize(hasPermi = "flowable:model:save")
    @PostMapping(value = "/save")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult save(@RequestBody ModelRequest modelRequest) {
        Assert.notNull(modelRequest.getKey(), "key is null");
        Assert.notNull(modelRequest.getName(), "name is null");
        Assert.notNull(modelRequest.getCategory(), "category is null");

        checkModelKeyExists(modelRequest.getKey());

        Model model = repositoryService.newModel();
        model.setKey(modelRequest.getKey());
        model.setName(modelRequest.getName());
        model.setVersion(1);
        model.setMetaInfo(modelRequest.getDescription());
        model.setTenantId(SecurityUtils.getParentDeptId() + "");
        model.setCategory(modelRequest.getCategory());
        repositoryService.saveModel(model);

        return AjaxResult.success();
    }

    @ApiOperation(value = "复制流程模型")
    @PreAuthorize(hasPermi = "flowable:model:copy")
    @PutMapping(value = "/copy")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult copy(@RequestBody ModelRequest modelRequest) {
        Assert.notNull(modelRequest.getId(), "id is null");
        Model model = repositoryService.getModel(modelRequest.getId());
        if (model == null) {
            throw new FlowableException("Cannot find model by id:" + modelRequest.getId());
        }
        byte[] editor = repositoryService.getModelEditorSource(modelRequest.getId());
        if (editor == null || editor.length == 0) {
            throw new FlowableException("Cannot find modelEditor by id:" + modelRequest.getId());
        }
        managementService.executeCommand(new SaveModelEditorCmd(SaveModelEditorCmd.TYPE_3, null, null, null, null,
                null, editor, model.getTenantId()));
        return AjaxResult.success();
    }

    @ApiOperation(value = "删除流程模型")
    @PreAuthorize(hasPermi = "flowable:model:delete")
    @DeleteMapping(value = "/delete")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult delete(@RequestParam String ids, @RequestParam(required = false) boolean cascade) {
        if (ids == null || ids.trim().length() == 0) {
            return AjaxResult.error("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        for (String id : idsArr) {
            Model model = getModelById(id);
            if (cascade && model.getDeploymentId() != null) {
                repositoryService.deleteDeployment(model.getDeploymentId(), cascade);
            }
            repositoryService.deleteModel(model.getId());
        }

        return AjaxResult.success();
    }

    @ApiOperation(value = "保存流程设计")
    @PreAuthorize(hasPermi = "flowable:model:saveModelEditor")
    @PutMapping(value = "/saveModelEditor")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult saveModelEditor(@RequestBody ModelRequest modelRequest) {
        Assert.notNull(modelRequest.getId(), "id is null");
        Assert.notNull(modelRequest.getEditor(), "editor is null");
        managementService.executeCommand(new SaveModelEditorCmd("1", modelRequest.getId(), null, null, null, null,
                modelRequest.getEditor().getBytes(StandardCharsets.UTF_8), modelRequest.getTenantId()));
        return AjaxResult.success();
    }

    @ApiOperation(value = "部署流程模型")
    @PreAuthorize(hasPermi = "flowable:model:deployModel")
    @PostMapping(value = "/deploy")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult deployModel(@RequestBody ModelRequest modelRequest) {
        Assert.notNull(modelRequest.getId(), "id is null");
        modelRequest.setTenantId(SecurityUtils.getParentDeptId() + "");
        managementService.executeCommand(new DeployModelCmd(modelRequest.getId()));
        return new AjaxResult();
    }

    @ApiOperation(value = "导入流程模型")
    @PreAuthorize(hasPermi = "flowable:model:doImport")
    @PostMapping(value = "/import")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult doImport(@RequestParam(required = false) String tenantId, HttpServletRequest request) throws IOException {
        if (!(request instanceof MultipartHttpServletRequest)) {
            throw new IllegalArgumentException("request must instance of MultipartHttpServletRequest");
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        if (multipartRequest.getFileMap().size() == 0) {
            throw new IllegalArgumentException("request file is empty");
        }
        MultipartFile file = multipartRequest.getFileMap().values().iterator().next();
        String fileName = file.getOriginalFilename();
        boolean isFileNameInValid =
                ObjectUtils.isEmpty(fileName) || !(fileName.endsWith(".bpmn20.xml") || fileName.endsWith(".bpmn") || fileName.toLowerCase().endsWith(".bar") || fileName.toLowerCase().endsWith(".zip"));
        if (isFileNameInValid) {
            throw new IllegalArgumentException("Request file must end with .bpmn20.xml,.bpmn|,.bar,.zip");
        }

        boolean isBpmnFile = fileName.endsWith(".bpmn20.xml") || fileName.endsWith(".bpmn");
        if (isBpmnFile) {
            managementService.executeCommand(new SaveModelEditorCmd(SaveModelEditorCmd.TYPE_2, null, null, null, null
                    , null, file.getBytes(), tenantId));
        } else if (fileName.toLowerCase().endsWith(FlowableConstant.FILE_EXTENSION_BAR) || fileName.toLowerCase().endsWith(FlowableConstant.FILE_EXTENSION_ZIP)) {
            try {
                ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());
                ZipEntry entry = zipInputStream.getNextEntry();
                while (entry != null) {
                    if (!entry.isDirectory()) {
                        String entryName = entry.getName();
                        byte[] bytes = IoUtil.readInputStream(zipInputStream, entryName);
                        managementService.executeCommand(new SaveModelEditorCmd(SaveModelEditorCmd.TYPE_2, null, null
                                , null, null, null, bytes, tenantId));
                    }
                    entry = zipInputStream.getNextEntry();
                }
            } catch (Exception e) {
                throw new FlowableException("problem reading zip input stream", e);
            }
        }
        return AjaxResult.success("导入成功");
    }
}
