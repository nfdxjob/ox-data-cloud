package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dshubs.odc.domain.entity.LovValue;
import org.apache.ibatis.annotations.Mapper;


/**
* 独立值集数据数据访问层
*
* @author wangxian 2022-04-07
*/
@Mapper
public interface LovValueMapper extends BaseMapper<LovValue> {

}
