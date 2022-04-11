package org.dshubs.odc.app.service;

import org.dshubs.odc.api.vo.LovValueVO;
import org.dshubs.odc.domain.entity.Lov;
import org.dshubs.odc.domain.entity.LovValue;
import org.dshubs.odc.mybatis.app.service.IBaseService;

import java.util.List;


/**
 * 值集逻辑控制层
 *
 * @author wangxian 2022-04-07
 */
public interface LovService extends IBaseService<Lov> {

    /**
     * 根据值集编码查询值集数据
     *
     * @param lovCode 值集编码
     * @return 值集
     */
    List<LovValueVO> listLovData(String lovCode);
}

