package org.dshubs.odc.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dshubs.odc.domain.entity.EmailServer;
import org.apache.ibatis.annotations.Mapper;


/**
* 邮件服务数据访问层
*
* @author daisicheng 2022-03-21
*/
@Mapper
public interface MailServerMapper extends BaseMapper<EmailServer> {

}
