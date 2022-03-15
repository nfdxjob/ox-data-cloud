package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.core.domain.AuditEntity;

/**
* 角色
*
* @author wangxian 2022-03-04
*/
@Data
@ApiModel("角色模型")
@EqualsAndHashCode(callSuper=false)
@TableName("oauth_role")
public class OauthRole extends AuditEntity {


    /**
     * 
     */
    @ApiModelProperty("ID")
    private Long roleId;

    /**
     * 用户Id
     */
    @ApiModelProperty("用户Id")
    private String roleCode;

    /**
     * 员工ID
     */
    @ApiModelProperty("员工ID")
    private String roleName;

    /**
     * 父级角色编码
     */
    @ApiModelProperty("父级角色编码")
    private String parentRoleCode;

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
