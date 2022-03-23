package org.dshubs.odc.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.dshubs.odc.app.service.MailServerService;
import org.dshubs.odc.domain.entity.MailServer;
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
public class MailServerServiceImpl extends BaseServiceImpl<MailServerMapper, MailServer> implements MailServerService {

    @Override
    public List<MailServer> listMailServer() {
        return this.baseMapper.selectList(this.getSelectQueryWrapper());
    }

    @Override
    public PageData<MailServer> pageMailServer(PageRequest page, MailServer query) {
        LambdaQueryWrapper<MailServer> lambdaQueryWrapper = this.getSelectQueryWrapper();
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(query.getServerCode()), MailServer::getServerCode, query.getServerCode())
                .eq(StringUtils.isNotBlank(query.getServerName()), MailServer::getServerName, query.getServerName());
        Page<MailServer> mailServerPage = this.baseMapper.selectPage(super.getPageQuery(page), lambdaQueryWrapper);
        return super.getPageData(mailServerPage);
    }


    /**
     * 获取查询QueryWrapper
     *
     * @return QueryWrapper
     */
    private LambdaQueryWrapper<MailServer> getSelectQueryWrapper() {
        LambdaQueryWrapper<MailServer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(MailServer::getServerId, MailServer::getServerCode, MailServer::getServerName, MailServer::getEnabledFlag, MailServer::getHost
                , MailServer::getPort, MailServer::getTryTimes, MailServer::getUsername, MailServer::getSender, MailServer::getProtocol);
        return lambdaQueryWrapper;
    }

}

