package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.MassageService;
import org.dshubs.odc.infra.mapper.MassageMapper;
import org.dshubs.odc.domain.entity.Massage;
import org.springframework.stereotype.Service;


/**
 * 消息信息逻辑控制层
 *
 * @author daisicheng 2022-03-21
 */
@Service
public class MassageServiceImpl extends BaseServiceImpl<MassageMapper,Massage> implements MassageService {

}

