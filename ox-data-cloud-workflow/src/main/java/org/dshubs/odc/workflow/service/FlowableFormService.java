package org.dshubs.odc.workflow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dshubs.odc.workflow.entity.FlowableForm;

import java.util.List;
import java.util.Map;

/**
 * Flowable 流程表单Service
 *
 * @author : 湖南牛数商智信息科技有限公司
 */
public interface FlowableFormService extends IService<FlowableForm> {
    /**
     * 分页查询流程表单
     *
     * @param page         分页参数
     * @param flowableForm Flowable
     * @return Ipage
     */
    IPage<FlowableForm> list(IPage<FlowableForm> page, FlowableForm flowableForm);


    List<Map<String, String>> getUserList();

}
