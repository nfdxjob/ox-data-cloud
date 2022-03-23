package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.NoticeService;
import org.dshubs.odc.infra.mapper.NoticeMapper;
import org.dshubs.odc.domain.entity.Notice;
import org.springframework.stereotype.Service;


/**
 * 公告信息逻辑控制层
 *
 * @author daisicheng 2022-03-21
 */
@Service
public class NoticeServiceImpl extends BaseServiceImpl<NoticeMapper,Notice> implements NoticeService {

}

