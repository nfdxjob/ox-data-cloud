package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.mybatis.domain.AuditEntity;

/**
 * 操作审计日志
 *
 * @author wangxian 2022-03-04
 */
@Data
@ApiModel("操作审计日志模型")
@EqualsAndHashCode(callSuper = false)
@TableName("audit_operation_log")
public class AuditOperationLog extends AuditEntity {


    /**
     *
     */
    @ApiModelProperty("ID")
    @TableId
    private Long logId;

    /**
     * 应用名称
     */
    @ApiModelProperty("应用名称")
    private String applicationName;

    /**
     * 菜单ID
     */
    @ApiModelProperty("菜单ID")
    private Long menuId;

    /**
     * 操作用户ID
     */
    @ApiModelProperty("操作用户ID")
    private Long userId;

    /**
     * 操作内容,如创建用户
     */
    @ApiModelProperty("操作内容,如创建用户")
    private String auditContent;

    /**
     * 请求IP
     */
    @ApiModelProperty("请求IP")
    private String requestIp;

    /**
     * 请求地址
     */
    @ApiModelProperty("请求地址")
    private String requestUri;

    /**
     * 用户给代理
     */
    @ApiModelProperty("用户给代理")
    private String requestUserAgent;

    /**
     * 请求方式
     */
    @ApiModelProperty("请求方式")
    private String requestMethod;

    /**
     * 操作耗时
     */
    @ApiModelProperty("操作耗时")
    private Long timeConsuming;

    /**
     * 成功标识,1-成功,0-失败
     */
    @ApiModelProperty("成功标识,1-成功,0-失败")
    private Integer successFlag;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

}
