package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.mybatis.domain.AuditEntity;

/**
 * 短信服务
 *
 * @author daisicheng 2022-03-21
 */
@Data
@ApiModel("短信服务模型")
@EqualsAndHashCode(callSuper = false)
@TableName("omsg_sms_server")
public class SmsServer extends AuditEntity {


    /**
     * 短信服务主键
     */
    @ApiModelProperty("短信服务主键")
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
     * 短信服务类型
     */
    @ApiModelProperty("短信服务类型")
    private String serverTypeCode;

    /**
     * AccessKeyId
     */
    @ApiModelProperty("AccessKeyId")
    private String accessKey;

    /**
     * AccessKeySecret
     */
    @ApiModelProperty("AccessKeySecret")
    private String accessKeySecret;

    /**
     * 短信签名
     */
    @ApiModelProperty("短信签名")
    private String signName;

    /**
     * 启用标识
     */
    @ApiModelProperty("启用标识")
    private Integer enabledFlag;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

}
