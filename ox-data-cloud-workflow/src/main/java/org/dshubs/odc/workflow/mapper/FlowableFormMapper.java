package org.dshubs.odc.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dshubs.odc.workflow.entity.FlowableForm;

import java.util.List;
import java.util.Map;

/**
 * 流程表单Mapper
 *
 * @author guodong
 */
@Mapper
public interface FlowableFormMapper extends BaseMapper<FlowableForm> {
    /**
     * 查询流程表单列表
     *
     * @param page
     * @param entity
     * @return
     */
    public List<FlowableForm> list(IPage<FlowableForm> page, @Param("entity") FlowableForm entity);


    List<Map<String,String>> getUserList();
}
