package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.mybatis.domain.AuditEntity;

/**
* 用户角色
*
* @author wangxian 2022-03-04
*/
@Data
@ApiModel("用户角色模型")
@EqualsAndHashCode(callSuper=false)
@TableName("oauth_user_role")
public class OauthUserRole extends AuditEntity {


    /**
     * 
     */
    @ApiModelProperty("ID")
    @TableId
    private Long id;

    /**
     * 用户Id
     */
    @ApiModelProperty("用户Id")
    private Long userId;

    /**
     * 橘色ID
     */
    @ApiModelProperty("橘色ID")
    private Long roleId;

    /**
     * 是否默认
     */
    @ApiModelProperty("是否默认")
    private Integer defaultRole;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

}
