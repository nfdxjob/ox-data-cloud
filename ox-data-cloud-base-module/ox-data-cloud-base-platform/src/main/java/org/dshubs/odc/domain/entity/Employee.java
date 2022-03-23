package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.core.domain.AuditEntity;

/**
 * 员工信息
 *
 * @author wangxian 2022-03-02
 */
@Data
@ApiModel("员工信息模型")
@EqualsAndHashCode(callSuper = false)
@TableName("opfm_employee")
public class Employee extends AuditEntity {


    /**
     *
     */
    @ApiModelProperty("ID")
    @TableId
    private Long employeeId;

    /**
     * 员工工号
     */
    @ApiModelProperty("员工工号")
    private String employeeNum;

    /**
     * 域账号
     */
    @ApiModelProperty("域账号")
    private String domainAccount;

    /**
     * 员工名称
     */
    @ApiModelProperty("员工名称")
    private String employeeName;

    /**
     * 所属组织
     */
    @ApiModelProperty("所属组织")
    private String orgCode;

    /**
     * 组织名称
     */
    @ApiModelProperty("组织名称")
    private String orgName;

    /**
     * 员工邮箱
     */
    @ApiModelProperty("员工邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 家庭地址
     */
    @ApiModelProperty("家庭地址")
    private String homeAddress;

    /**
     * 岗位编码
     */
    @ApiModelProperty("岗位编码")
    private String positionCode;

    /**
     * 岗位名称
     */
    @ApiModelProperty("岗位名称")
    private String positionName;

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
