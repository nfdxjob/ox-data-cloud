package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.AuditOperationLogDetailService;
import org.dshubs.odc.infra.mapper.AuditOperationLogDetailMapper;
import org.dshubs.odc.domain.entity.AuditOperationLogDetail;
import org.springframework.stereotype.Service;


/**
 * 操作审计日志详情逻辑控制层
 *
 * @author wangxian 2022-03-04
 */
@Service
public class AuditOperationLogDetailServiceImpl extends BaseServiceImpl<AuditOperationLogDetailMapper,AuditOperationLogDetail> implements AuditOperationLogDetailService {

}

