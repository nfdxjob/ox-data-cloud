package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.mybatis.domain.AuditEntity;

/**
 * 用户消息
 *
 * @author daisicheng 2022-03-21
 */
@Data
@ApiModel("用户消息模型")
@EqualsAndHashCode(callSuper = false)
@TableName("omsg_user_message")
public class UserMessage extends AuditEntity {


    /**
     * 用户消息主键
     */
    @ApiModelProperty("用户消息主键")
    @TableId
    private Long userMessageId;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 消息ID
     */
    @ApiModelProperty("消息ID")
    private Long messageId;

    /**
     * 已读标识
     */
    @ApiModelProperty("已读标识")
    private Integer readFlag;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

    /**
     * 来源租户ID
     */
    @ApiModelProperty("来源租户ID")
    private Long fromTenantId;

    /**
     * 行版本号，用来处理锁
     */
    @ApiModelProperty("行版本号，用来处理锁")
    private Long objectVersionNumber;

}
