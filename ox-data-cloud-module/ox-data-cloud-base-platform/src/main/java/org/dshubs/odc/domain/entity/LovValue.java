package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.mybatis.domain.AuditEntity;

/**
 * 独立值集数据
 *
 * @author wangxian 2022-04-07
 */
@Data
@ApiModel("独立值集数据模型")
@EqualsAndHashCode(callSuper = false)
@TableName("opfm_lov_value")
public class LovValue extends AuditEntity {


    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Long lovValueId;

    /**
     * 值集ID
     */
    @ApiModelProperty("值集ID")
    @TableId
    private String lovId;

    /**
     * 值集编码
     */
    @ApiModelProperty("值集编码")
    private String lovCode;

    /**
     * 值
     */
    @ApiModelProperty("值")
    private String value;

    /**
     * 含义
     */
    @ApiModelProperty("含义")
    private String meaning;

    /**
     * 标记
     */
    @ApiModelProperty("标记")
    private String tag;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String descirption;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private String orderSeq;

    /**
     * 是否启用
     */
    @ApiModelProperty("是否启用")
    private Integer enabledFlag;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

}
