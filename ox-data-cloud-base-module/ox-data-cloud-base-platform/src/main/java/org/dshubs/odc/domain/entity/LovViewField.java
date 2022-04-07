package org.dshubs.odc.domain.entity;

import org.dshubs.odc.core.domain.AuditEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 值集视图字段
 *
 * @author wangxian 2022-04-07
 */
@Data
@ApiModel("值集视图字段模型")
@EqualsAndHashCode(callSuper=false)
@TableName("opfm_lov_view_field")
public class LovViewField extends AuditEntity {


    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    @TableId
    private Long viewFieldId;

    /**
     * 值集视图ID
     */
    @ApiModelProperty("值集视图ID")
    private String viewId;

    /**
     * 值集ID
     */
    @ApiModelProperty("值集ID")
    private String lovId;

    /**
     * 字段名称
     */
    @ApiModelProperty("字段名称")
    private String fieldName;

    /**
     * 显示名称
     */
    @ApiModelProperty("显示名称")
    private String displayName;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private String orderSeq;

    /**
     * 是否表格字段
     */
    @ApiModelProperty("是否表格字段")
    private Integer tableField;

    /**
     * 是否表格查询字段
     */
    @ApiModelProperty("是否表格查询字段")
    private Integer tableQueryField;

    /**
     * 字段展示宽度
     */
    @ApiModelProperty("字段展示宽度")
    private String filedWidth;

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
