package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.mybatis.domain.AuditEntity;

/**
 * 组织部门
 *
 * @author wangxian 2022-03-02
 */
@Data
@ApiModel("组织部门模型")
@EqualsAndHashCode(callSuper = false)
@TableName("opfm_organization")
public class Organization extends AuditEntity {


    /**
     *
     */
    @ApiModelProperty("组织ID")
    @TableId
    private Long orgId;

    /**
     * 组织编码
     */
    @ApiModelProperty("组织编码")
    private String orgCode;

    /**
     * 组织名称
     */
    @ApiModelProperty("组织名称")
    private String orgName;


    /**
     * 父级编码
     */
    @ApiModelProperty("父级编码")
    private String parentCode;


    /**
     * 同级别排序
     */
    @ApiModelProperty("同级别排序")
    private Integer sort;


    /**
     * 组织级别
     */
    @ApiModelProperty("组织级别")
    private String orgLevel;

    /**
     * C:公司,D:部门
     */
    @ApiModelProperty("C:公司,D:部门")
    private String orgType;

    /**
     * 组织领导
     */
    @ApiModelProperty("组织领导")
    private String orgLeader;

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
