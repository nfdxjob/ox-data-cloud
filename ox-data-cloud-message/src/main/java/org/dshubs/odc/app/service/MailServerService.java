package org.dshubs.odc.app.service;

import org.dshubs.odc.domain.entity.MailServer;
import org.dshubs.odc.mybatis.app.service.IBaseService;
import org.dshubs.odc.mybatis.infra.pagination.PageData;
import org.dshubs.odc.mybatis.infra.pagination.PageRequest;

import java.util.List;


/**
 * 邮件服务逻辑控制层
 *
 * @author daisicheng 2022-03-21
 */
public interface MailServerService extends IBaseService<MailServer> {

    /**
     * 查询邮箱服务信息
     *
     * @return 邮箱服务信息列表
     */
    List<MailServer> listMailServer();

    /**
     * 分页查询邮箱服务信息
     *
     * @param page  分页参数
     * @param query 查询条件参数
     * @return 分页邮箱服务列表
     */
    PageData<MailServer> pageMailServer(PageRequest page, MailServer query);

    /**
     * 查询邮箱服务详细信息
     *
     * @param mailServerId 服务主键id
     * @return 邮箱服务详细信息
     */
//    MailServer getMailServer(Long mailServerId);

}

