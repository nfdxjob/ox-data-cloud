package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dshubs.odc.domain.entity.AuditOperationLogDetail;
import org.apache.ibatis.annotations.Mapper;


/**
* 操作审计日志详情数据访问层
*
* @author wangxian 2022-03-04
*/
@Mapper
public interface AuditOperationLogDetailMapper extends BaseMapper<AuditOperationLogDetail> {

}
