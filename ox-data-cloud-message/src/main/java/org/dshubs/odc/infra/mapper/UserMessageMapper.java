package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dshubs.odc.domain.entity.UserMessage;
import org.apache.ibatis.annotations.Mapper;


/**
* 用户消息数据访问层
*
* @author daisicheng 2022-03-21
*/
@Mapper
public interface UserMessageMapper extends BaseMapper<UserMessage> {

}
