package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.LovValueService;
import org.dshubs.odc.infra.mapper.LovValueMapper;
import org.dshubs.odc.domain.entity.LovValue;
import org.springframework.stereotype.Service;


/**
 * 独立值集数据逻辑控制层
 *
 * @author wangxian 2022-04-07
 */
@Service
public class LovValueServiceImpl extends BaseServiceImpl<LovValueMapper,LovValue> implements LovValueService {

}

