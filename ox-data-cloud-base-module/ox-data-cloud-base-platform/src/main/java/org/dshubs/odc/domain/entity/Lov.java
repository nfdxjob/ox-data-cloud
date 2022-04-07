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
 * 值集
 *
 * @author wangxian 2022-04-07
 */
@Data
@ApiModel("值集模型")
@EqualsAndHashCode(callSuper=false)
@TableName("opfm_lov")
public class Lov extends AuditEntity {


    /**
     * 
     */
    @ApiModelProperty("主键")
    @TableId
    private Long lovId;

    /**
     * LOV代码
     */
    @ApiModelProperty("LOV代码")
    private String lovCode;

    /**
     * LOV数据类型: URL/SQL/FIXED
     */
    @ApiModelProperty("LOV数据类型: URL/SQL/FIXED")
    private String lovTypeCode;

    /**
     * 目标路由
     */
    @ApiModelProperty("目标路由")
    private String routeName;

    /**
     * 值集名称
     */
    @ApiModelProperty("值集名称")
    private String lovName;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     * 
     */
    @ApiModelProperty("")
    private String parentLovCode;

    /**
     * 父级值集租户ID
     */
    @ApiModelProperty("父级值集租户ID")
    private Long parentTenantId;

    /**
     * 自定义sql
     */
    @ApiModelProperty("自定义sql")
    private String customSql;

    /**
     * 查询URL
     */
    @ApiModelProperty("查询URL")
    private String customUrl;

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
     * 是否必须分页
     */
    @ApiModelProperty("是否必须分页")
    private Integer mustPageFlag;

    /**
     * 翻译sql
     */
    @ApiModelProperty("翻译sql")
    private String translationSql;

    /**
     * 是否公开权限，0:不公开 1:公开
     */
    @ApiModelProperty("是否公开权限，0:不公开 1:公开")
    private Integer publicFlag;

    /**
     * 加密字段
     */
    @ApiModelProperty("加密字段")
    private String encryptField;

    /**
     * 存储解密字段
     */
    @ApiModelProperty("存储解密字段")
    private String decryptField;

    /**
     * 请求方式，值集：HPFM.REQUEST_METHOD
     */
    @ApiModelProperty("请求方式，值集：HPFM.REQUEST_METHOD")
    private String requestMethod;

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
