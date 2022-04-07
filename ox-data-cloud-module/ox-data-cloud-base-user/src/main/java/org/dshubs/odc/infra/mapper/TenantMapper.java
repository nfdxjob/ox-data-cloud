package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dshubs.odc.domain.entity.Tenant;

/**
 * @author create by wangxian 2022/2/19
 */
@Mapper
public interface TenantMapper extends BaseMapper<Tenant> {
}
