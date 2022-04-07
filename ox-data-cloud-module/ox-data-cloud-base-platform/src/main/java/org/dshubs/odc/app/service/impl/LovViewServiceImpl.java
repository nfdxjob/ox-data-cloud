package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.LovViewService;
import org.dshubs.odc.infra.mapper.LovViewMapper;
import org.dshubs.odc.domain.entity.LovView;
import org.springframework.stereotype.Service;


/**
 * 值集视图逻辑控制层
 *
 * @author wangxian 2022-04-07
 */
@Service
public class LovViewServiceImpl extends BaseServiceImpl<LovViewMapper,LovView> implements LovViewService {

}

