package org.dshubs.odc.workflow.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.dshubs.odc.workflow.common.AjaxResult;
import org.dshubs.odc.workflow.common.annotation.PreAuthorize;
import org.dshubs.odc.workflow.entity.FlowableForm;
import org.dshubs.odc.workflow.service.FlowableFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * 流程表单Controller
 *
 * @author 湖南牛数商智信息科技有限公司
 */
@RestController
@RequestMapping("/flowable/form")
public class FlowableFormController {
    @Autowired
    private FlowableFormService flowableFormService;

    /**
     * 自定义查询列表
     *
     * @param flowableForm request form
     * @param current 当前页
     * @param size 页数
     * @return AjaxResult
     */
    @PreAuthorize(hasPermi = "flowable:form:list")
    @GetMapping(value = "/list")
    public AjaxResult list(FlowableForm flowableForm, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<FlowableForm> pageList = flowableFormService.list(new Page<FlowableForm>(current, size), flowableForm);
        return AjaxResult.success(pageList);
    }

    @PreAuthorize(hasPermi = "flowable:form:queryById")
    @GetMapping(value = "/queryById")
    public AjaxResult queryById(@RequestParam String id) {
        FlowableForm flowableForm = flowableFormService.getById(id);
        return AjaxResult.success(flowableForm);
    }

    /**
     * @param flowableForm request body
     * @return ajaxResult
     */
    @ApiOperation(value = "新增流程表单")
    @PreAuthorize(hasPermi = "flowable:form:save")
    @PostMapping(value = "/save")
    public AjaxResult save(@Valid @RequestBody FlowableForm flowableForm) {
        flowableFormService.save(flowableForm);
        return AjaxResult.success();
    }

    /**
     * @param flowableForm request body
     * @return AjaxResult
     */
    @ApiOperation(value = "修改流程表单")
    @PreAuthorize(hasPermi = "flowable:form:update")
    @PutMapping(value = "/update")
    public AjaxResult update(@Valid @RequestBody FlowableForm flowableForm) {
        flowableFormService.updateById(flowableForm);
        return AjaxResult.success();
    }

    /**
     * @param ids id集合列表
     * @return AjaxResult
     */
    @ApiOperation(value = "删除流程表单")
    @PreAuthorize(hasPermi = "flowable:form:delete")
    @DeleteMapping(value = "/delete")
    public AjaxResult delete(@RequestParam String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return AjaxResult.error("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            flowableFormService.removeByIds(Arrays.asList(idsArr));
        } else {
            flowableFormService.removeById(idsArr[0]);
        }
        return AjaxResult.success();
    }
}
