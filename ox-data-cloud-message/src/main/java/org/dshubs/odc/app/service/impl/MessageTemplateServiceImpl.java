package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.MessageTemplateService;
import org.dshubs.odc.infra.mapper.MessageTemplateMapper;
import org.dshubs.odc.domain.entity.MessageTemplate;
import org.springframework.stereotype.Service;


/**
 * 消息模板逻辑控制层
 *
 * @author daisicheng 2022-03-21
 */
@Service
public class MessageTemplateServiceImpl extends BaseServiceImpl<MessageTemplateMapper,MessageTemplate> implements MessageTemplateService {

}

