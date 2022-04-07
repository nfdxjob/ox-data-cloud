package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.mybatis.domain.AuditEntity;

/**
 * 值集视图
 *
 * @author wangxian 2022-04-07
 */
@Data
@ApiModel("值集视图模型")
@EqualsAndHashCode(callSuper=false)
@TableName("opfm_lov_view")
public class LovView extends AuditEntity {


    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    @TableId
    private Long lovViewId;

    /**
     * 值集视图编码
     */
    @ApiModelProperty("值集视图编码")
    private String lovViewCode;

    /**
     * 值集视图名字
     */
    @ApiModelProperty("值集视图名字")
    private String viewName;

    /**
     * 值集ID
     */
    @ApiModelProperty("值集ID")
    private String lovId;

    /**
     * 值字段
     */
    @ApiModelProperty("值字段")
    private String valueField;

    /**
     * 显示字段
     */
    @ApiModelProperty("显示字段")
    private String displayField;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 视图宽度
     */
    @ApiModelProperty("视图宽度")
    private String width;

    /**
     * 视图高度
     */
    @ApiModelProperty("视图高度")
    private String height;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private String pageSize;

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
