package org.dshubs.odc.domain.entity;

import org.dshubs.odc.core.domain.AuditEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
* 用户员工
*
* @author wangxian 2022-03-04
*/
@Data
@ApiModel("用户员工模型")
@EqualsAndHashCode(callSuper=false)
@TableName("user_employee")
public class UserEmployee extends AuditEntity {


    /**
     * 
     */
    @ApiModelProperty("ID")
    private Long id;

    /**
     * 用户Id
     */
    @ApiModelProperty("用户Id")
    private Long userId;

    /**
     * 员工ID
     */
    @ApiModelProperty("员工ID")
    private Long employeeId;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 员工工号
     */
    @ApiModelProperty("员工工号")
    private String employeeNum;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

}
