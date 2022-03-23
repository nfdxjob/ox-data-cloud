package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dshubs.odc.domain.entity.MessageTemplate;
import org.apache.ibatis.annotations.Mapper;


/**
* 消息模板数据访问层
*
* @author daisicheng 2022-03-21
*/
@Mapper
public interface MessageTemplateMapper extends BaseMapper<MessageTemplate> {

}
