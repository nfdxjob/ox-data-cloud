package org.dshubs.odc.app.service;

import org.dshubs.odc.domain.entity.LovValue;
import org.dshubs.odc.mybatis.app.service.IBaseService;

import java.util.List;


/**
 * 独立值集数据逻辑控制层
 *
 * @author wangxian 2022-04-07
 */
public interface LovValueService extends IBaseService<LovValue>  {

    /**
     * 根据值集ID查询值集值
     * @param lovId 值集ID
     * @return 值集值列表
     */
    List<LovValue> listByLovId(Long lovId);
}

