package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dshubs.odc.domain.entity.Position;
import org.apache.ibatis.annotations.Mapper;


/**
* 岗位数据访问层
*
* @author wangxian 2022-03-02
*/
@Mapper
public interface PositionMapper extends BaseMapper<Position> {

}
