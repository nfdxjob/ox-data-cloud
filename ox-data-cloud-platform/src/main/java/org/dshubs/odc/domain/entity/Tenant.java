package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author create by wangxian 2022/2/19
 */
@TableName(value = "tenant")
@Data
public class Tenant {

    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    @ApiModelProperty(value = "租户编码")
    private String tenantCode;

    @ApiModelProperty(value = "租户名称")
    private String tenantName;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabledFlag;

    @ApiModelProperty(value = "数据版本")
    @Version
    private Long version;

    @ApiModelProperty(value = "创建人ID")
    private Long createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creationDate;

    @ApiModelProperty(value = "更新人ID")
    private Long lastUpdatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime lastUpdateDate;
}
