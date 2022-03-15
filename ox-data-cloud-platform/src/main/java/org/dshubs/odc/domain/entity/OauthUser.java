package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.core.domain.AuditEntity;

import java.time.LocalDateTime;

/**
 * 用户信息
 *
 * @author wangxian 2022-03-04
 */
@Data
@ApiModel("用户信息模型")
@EqualsAndHashCode(callSuper = false)
@TableName("oauth_user")
public class OauthUser extends AuditEntity {


    /**
     *
     */
    @ApiModelProperty("ID")
    @TableId
    private Long userId;

    /**
     * 登录名
     */
    @ApiModelProperty("登录名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;


    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String realName;


    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickName;


    /**
     * 头像地址
     */
    @ApiModelProperty("头像地址")
    private String avatarAddress;

    /**
     * 最后登录时间
     */
    @ApiModelProperty("最后登录时间")
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录IP
     */
    @ApiModelProperty("最后登录IP")
    private String lastLoginIp;

    /**
     * 密码错误次数
     */
    @ApiModelProperty("密码错误次数")
    private Integer passwordErrorTimes;

    /**
     * 帐户未锁定,锁定-1,未锁定-0
     */
    @ApiModelProperty("帐户未锁定,锁定-1,未锁定-0")
    private Integer accountNonLocked;

    /**
     * 是否启用,启用-1,未启用-0
     */
    @ApiModelProperty("是否启用,启用-1,未启用-0")
    private Integer enabled;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

}
