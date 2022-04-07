package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.LovService;
import org.dshubs.odc.infra.mapper.LovMapper;
import org.dshubs.odc.domain.entity.Lov;
import org.springframework.stereotype.Service;


/**
 * 值集逻辑控制层
 *
 * @author wangxian 2022-04-07
 */
@Service
public class LovServiceImpl extends BaseServiceImpl<LovMapper,Lov> implements LovService {

}

