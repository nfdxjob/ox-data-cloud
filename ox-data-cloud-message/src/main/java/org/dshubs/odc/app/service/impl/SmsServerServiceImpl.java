package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.SmsServerService;
import org.dshubs.odc.infra.mapper.SmsServerMapper;
import org.dshubs.odc.domain.entity.SmsServer;
import org.springframework.stereotype.Service;


/**
 * 短信服务逻辑控制层
 *
 * @author daisicheng 2022-03-21
 */
@Service
public class SmsServerServiceImpl extends BaseServiceImpl<SmsServerMapper,SmsServer> implements SmsServerService {

}

