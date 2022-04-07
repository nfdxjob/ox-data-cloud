package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dshubs.odc.domain.entity.Lov;
import org.apache.ibatis.annotations.Mapper;


/**
* 值集数据访问层
*
* @author wangxian 2022-04-07
*/
@Mapper
public interface LovMapper extends BaseMapper<Lov> {

}
