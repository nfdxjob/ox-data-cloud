package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.LovViewFieldService;
import org.dshubs.odc.infra.mapper.LovViewFieldMapper;
import org.dshubs.odc.domain.entity.LovViewField;
import org.springframework.stereotype.Service;


/**
 * 值集视图字段逻辑控制层
 *
 * @author wangxian 2022-04-07
 */
@Service
public class LovViewFieldServiceImpl extends BaseServiceImpl<LovViewFieldMapper,LovViewField> implements LovViewFieldService {

}

