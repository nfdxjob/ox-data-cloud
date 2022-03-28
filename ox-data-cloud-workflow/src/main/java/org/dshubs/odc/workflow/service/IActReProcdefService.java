package org.dshubs.odc.workflow.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.dshubs.odc.workflow.entity.ActReProcdef;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author : 湖南牛数商智信息科技有限公司
 */
public interface IActReProcdefService extends IService<ActReProcdef> {

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public ActReProcdef selectActReProcdefById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param actReProcdef 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ActReProcdef> selectActReProcdefList(ActReProcdef actReProcdef);

    /**
     * 新增【请填写功能名称】
     *
     * @param actReProcdef 【请填写功能名称】
     * @return 结果
     */
    public int insertActReProcdef(ActReProcdef actReProcdef);

    /**
     * 修改【请填写功能名称】
     *
     * @param actReProcdef 【请填写功能名称】
     * @return 结果
     */
    public int updateActReProcdef(ActReProcdef actReProcdef);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteActReProcdefById(String id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteActReProcdefByIds(String[] ids);

    //根据租户查询数据
    List<ActReProcdef> selectByTenantId(String tenantId);
}
