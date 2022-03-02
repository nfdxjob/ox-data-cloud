package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.PositionService;
import org.dshubs.odc.infra.mapper.PositionMapper;
import org.dshubs.odc.domain.entity.Position;
import org.springframework.stereotype.Service;


/**
 * 岗位逻辑控制层
 *
 * @author wangxian 2022-03-02
 */
@Service
public class PositionServiceImpl extends BaseServiceImpl<PositionMapper,Position> implements PositionService {

}

