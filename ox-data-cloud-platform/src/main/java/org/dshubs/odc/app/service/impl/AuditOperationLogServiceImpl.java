package org.dshubs.odc.app.service.impl;

import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.app.service.AuditOperationLogService;
import org.dshubs.odc.infra.mapper.AuditOperationLogMapper;
import org.dshubs.odc.domain.entity.AuditOperationLog;
import org.springframework.stereotype.Service;


/**
 * 操作审计日志逻辑控制层
 *
 * @author wangxian 2022-03-04
 */
@Service
public class AuditOperationLogServiceImpl extends BaseServiceImpl<AuditOperationLogMapper,AuditOperationLog> implements AuditOperationLogService {

}

