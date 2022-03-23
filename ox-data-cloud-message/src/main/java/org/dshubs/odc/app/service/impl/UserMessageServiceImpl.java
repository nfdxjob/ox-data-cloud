package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.UserMessageService;
import org.dshubs.odc.infra.mapper.UserMessageMapper;
import org.dshubs.odc.domain.entity.UserMessage;
import org.springframework.stereotype.Service;


/**
 * 用户消息逻辑控制层
 *
 * @author daisicheng 2022-03-21
 */
@Service
public class UserMessageServiceImpl extends BaseServiceImpl<UserMessageMapper,UserMessage> implements UserMessageService {

}

