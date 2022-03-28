package org.dshubs.odc.workflow.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dshubs.odc.workflow.entity.ActReProcdef;
import org.dshubs.odc.workflow.mapper.ActReProcdefMapper;
import org.dshubs.odc.workflow.service.IActReProcdefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author 湖南牛数商智信息科技有限公司
 */
@Service
public class ActReProcdefServiceImpl extends ServiceImpl<ActReProcdefMapper, ActReProcdef> implements IActReProcdefService {

    @Autowired
    private ActReProcdefMapper actReProcdefMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public ActReProcdef selectActReProcdefById(String id) {
        return actReProcdefMapper.selectActReProcdefById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param actReProcdef 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ActReProcdef> selectActReProcdefList(ActReProcdef actReProcdef) {
        return actReProcdefMapper.selectActReProcdefList(actReProcdef);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param actReProcdef 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertActReProcdef(ActReProcdef actReProcdef) {
        return actReProcdefMapper.insertActReProcdef(actReProcdef);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param actReProcdef 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateActReProcdef(ActReProcdef actReProcdef) {
        return actReProcdefMapper.updateActReProcdef(actReProcdef);
    }


    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteActReProcdefById(String id) {
        return actReProcdefMapper.deleteActReProcdefById(id);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteActReProcdefByIds(String[] ids) {
        return actReProcdefMapper.deleteActReProcdefByIds(ids);
    }

    @Override
    public List<ActReProcdef> selectByTenantId(String tenantId) {
        return actReProcdefMapper.selectByTenantId(tenantId);
    }

}
