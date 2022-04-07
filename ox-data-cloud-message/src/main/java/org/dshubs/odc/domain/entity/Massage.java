package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.mybatis.domain.AuditEntity;

/**
 * 消息信息
 *
 * @author daisicheng 2022-03-21
 */
@Data
@ApiModel("消息信息模型")
@EqualsAndHashCode(callSuper = false)
@TableName("omsg_massage")
public class Massage extends AuditEntity {


    /**
     * 消息信息主键
     */
    @ApiModelProperty("消息信息主键")
    @TableId
    private Long messageId;

    /**
     * 消息类型， 1:邮箱 2:短信
     */
    @ApiModelProperty("消息类型， 1:邮箱 2:短信")
    private Integer messageTypeCode;

    /**
     * 模板代码
     */
    @ApiModelProperty("模板代码")
    private String templateCode;

    /**
     * 服务代码
     */
    @ApiModelProperty("服务代码")
    private String serverCode;

    /**
     * 消息标题
     */
    @ApiModelProperty("消息标题")
    private String subject;

    /**
     * 消息内容
     */
    @ApiModelProperty("消息内容")
    private String content;

    /**
     * 发送标记
     */
    @ApiModelProperty("发送标记")
    private Integer sendFlag;

    /**
     * 发送参数
     */
    @ApiModelProperty("发送参数")
    private String sendArgs;

    /**
     * 接收人
     */
    @ApiModelProperty("接收人")
    private String receiver;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

}
