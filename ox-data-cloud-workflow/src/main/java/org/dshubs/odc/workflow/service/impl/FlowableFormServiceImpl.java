package org.dshubs.odc.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dshubs.odc.workflow.entity.FlowableForm;
import org.dshubs.odc.workflow.mapper.FlowableFormMapper;
import org.dshubs.odc.workflow.service.FlowableFormService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 流程Service处理
 *
 * @author 湖南牛数商智信息科技有限公司
 */
@Service
public class FlowableFormServiceImpl extends ServiceImpl<FlowableFormMapper, FlowableForm> implements FlowableFormService {

    @Override
    public IPage<FlowableForm> list(IPage<FlowableForm> page, FlowableForm flowableForm) {
        return page.setRecords(baseMapper.list(page, flowableForm));
    }


    @Override
    public List<Map<String, String>> getUserList() {
        return baseMapper.getUserList();
    }
}
