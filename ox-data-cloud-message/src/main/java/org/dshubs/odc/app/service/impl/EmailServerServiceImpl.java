package org.dshubs.odc.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.app.service.EmailServerService;
import org.dshubs.odc.domain.entity.EmailServer;
import org.dshubs.odc.infra.mapper.MailServerMapper;
import org.dshubs.odc.mybatis.app.service.BaseServiceImpl;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 邮件服务逻辑控制层
 *
 * @author daisicheng 2022-03-21
 */
@Service
public class EmailServerServiceImpl extends BaseServiceImpl<MailServerMapper, EmailServer> implements EmailServerService {

    @Override
    public List<EmailServer> listMailServer() {
        return this.baseMapper.selectList(this.getSelectQueryWrapper());
    }

    @Override
    public PageData<EmailServer> pageMailServer(PageRequest page, EmailServer query) {
        LambdaQueryWrapper<EmailServer> lambdaQueryWrapper = this.getSelectQueryWrapper();
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(query.getServerCode()), EmailServer::getServerCode, query.getServerCode())
                .eq(StringUtils.isNotBlank(query.getServerName()), EmailServer::getServerName, query.getServerName());
        Page<EmailServer> mailServerPage = this.baseMapper.selectPage(super.getPageQuery(page), lambdaQueryWrapper);
        return super.getPageData(mailServerPage);
    }


    /**
     * 获取查询QueryWrapper
     *
     * @return QueryWrapper
     */
    private LambdaQueryWrapper<EmailServer> getSelectQueryWrapper() {
        LambdaQueryWrapper<EmailServer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(EmailServer::getServerId, EmailServer::getServerCode, EmailServer::getServerName, EmailServer::getEnabledFlag, EmailServer::getHost
                , EmailServer::getPort, EmailServer::getTryTimes, EmailServer::getUsername, EmailServer::getSender, EmailServer::getProtocol);
        return lambdaQueryWrapper;
    }

}

