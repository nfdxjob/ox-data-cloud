package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.dshubs.odc.domain.entity.LovValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
* 独立值集数据数据访问层
*
* @author wangxian 2022-04-07
*/
@Mapper
public interface LovValueMapper extends BaseMapper<LovValue> {

    List<LovValue> listByLovId(@Param("lovId") Long lovId);
}
