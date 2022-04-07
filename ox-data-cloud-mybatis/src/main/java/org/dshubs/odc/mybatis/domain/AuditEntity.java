package org.dshubs.odc.mybatis.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 基类
 *
 * @author create by wangxian 2021/12/4
 */
@Data
public abstract class AuditEntity {
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
    @TableField(
            update = "now()"
    )
    private LocalDateTime lastUpdateDate;
}
