package org.dshubs.odc.app.service.impl;

import com.google.common.collect.Lists;
import org.dshubs.odc.api.vo.LovValueVO;
import org.dshubs.odc.app.service.LovService;
import org.dshubs.odc.app.service.LovValueService;
import org.dshubs.odc.core.util.ConvertUtils;
import org.dshubs.odc.domain.entity.Lov;
import org.dshubs.odc.domain.entity.LovValue;
import org.dshubs.odc.infra.mapper.LovMapper;
import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 值集逻辑控制层
 *
 * @author wangxian 2022-04-07
 */
@Service
public class LovServiceImpl extends BaseServiceImpl<LovMapper, Lov> implements LovService {
    private final LovValueService lovValueService;

    public LovServiceImpl(LovValueService lovValueService) {
        this.lovValueService = lovValueService;
    }


    /**
     * 根据值集编码查询值集数据
     *
     * @param lovCode 值集编码
     * @return 值集
     */
    @Override
    public List<LovValueVO> listLovData(String lovCode) {
        Lov lov = this.baseMapper.selectByLovCode(lovCode);
        if (lov == null) {
            return Lists.newArrayList();
        }
        List<LovValue> lovValues = this.lovValueService.listByLovId(lov.getLovId());
        return ConvertUtils.convertList(lovValues, LovValueVO.class);
    }
}

