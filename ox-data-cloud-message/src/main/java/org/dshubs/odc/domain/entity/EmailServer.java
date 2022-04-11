package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.mybatis.domain.AuditEntity;

/**
 * 邮件服务
 *
 * @author daisicheng 2022-03-21
 */
@Data
@ApiModel("邮件服务模型")
@EqualsAndHashCode(callSuper = false)
@TableName("omsg_email_server")
public class EmailServer extends AuditEntity {


    /**
     * 邮件服务主键
     */
    @ApiModelProperty("邮件服务主键")
    @TableId
    private Long serverId;

    /**
     * 服务代码
     */
    @ApiModelProperty("服务代码")
    private String serverCode;

    /**
     * 服务名称
     */
    @ApiModelProperty("服务名称")
    private String serverName;

    /**
     * 服务器
     */
    @ApiModelProperty("服务器")
    private String host;

    /**
     * 端口
     */
    @ApiModelProperty("端口")
    private String port;

    /**
     * 重试次数
     */
    @ApiModelProperty("重试次数")
    private String tryTimes;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @JsonIgnore
    private String passwordEncrypted;

    /**
     * 发件人
     */
    @ApiModelProperty("发件人")
    private String sender;

    /**
     * 启用标识
     */
    @ApiModelProperty("启用标识")
    private Integer enabledFlag;

    /**
     * 协议，值集：SMTP
     */
    @ApiModelProperty("协议，值集：SMTP")
    private String protocol;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

}
