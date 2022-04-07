package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.mybatis.domain.AuditEntity;

/**
 * 数据权限
 *
 * @author wangxian 2022-03-04
 */
@Data
@ApiModel("数据权限模型")
@EqualsAndHashCode(callSuper = false)
@TableName("opfm_custom_data_permission")
public class CustomDataPermission extends AuditEntity {


    /**
     *
     */
    @ApiModelProperty("ID")
    @TableId
    private Long permissionId;

    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    private String permissionName;

    /**
     * SQL唯一ID,对应mapper包名加方法名称,如org.dshubs.odc.infra.mapper.selectList
     */
    @ApiModelProperty("SQL唯一ID,对应mapper包名加方法名称,如org.dshubs.odc.infra.mapper.selectList")
    private String sqlId;

    /**
     * SQL
     */
    @ApiModelProperty("SQL")
    private String permissionSql;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

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
