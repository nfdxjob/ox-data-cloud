package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.core.domain.AuditEntity;

/**
 * 操作审计日志详情
 *
 * @author wangxian 2022-03-04
 */
@Data
@ApiModel("操作审计日志详情模型")
@EqualsAndHashCode(callSuper = false)
@TableName("audit_operation_log_detail")
public class AuditOperationLogDetail extends AuditEntity {


    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 日志ID
     */
    @ApiModelProperty("日志ID")
    private Long logId;

    /**
     * 参数
     */
    @ApiModelProperty("参数")
    private String params;

    /**
     * 返回值
     */
    @ApiModelProperty("返回值")
    private String result;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

}
