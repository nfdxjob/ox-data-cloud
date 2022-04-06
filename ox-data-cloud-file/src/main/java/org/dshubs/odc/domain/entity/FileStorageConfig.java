package org.dshubs.odc.domain.entity;

import org.dshubs.odc.core.domain.AuditEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件存储配置
 *
 * @author zhou 2022-04-06
 */
@Data
@ApiModel("文件存储配置模型")
@EqualsAndHashCode(callSuper=false)
@TableName("ofile_storage_config")
public class FileStorageConfig extends AuditEntity implements Serializable {


    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    @TableId
    private Long storageConfigId;

    /**
     * 存储代码
     */
    @ApiModelProperty("存储代码")
    private String storageCode;

    /**
     * 存储类型,Aliyun,MinIO,Huawei,Tencent,Qiniu
     */
    @ApiModelProperty("存储类型,Aliyun,MinIO,Huawei,Tencent,Qiniu")
    private String storageType;

    /**
     * 端点
     */
    @ApiModelProperty("端点")
    private String endPoint;

    /**
     * 绑定域名
     */
    @ApiModelProperty("绑定域名")
    private String domain;

    /**
     * AccessKey
     */
    @ApiModelProperty("AccessKey")
    private String accessKey;

    /**
     * AccessKeySecret
     */
    @ApiModelProperty("AccessKeySecret")
    private String accessKeySecret;

    /**
     * 区域
     */
    @ApiModelProperty("区域")
    private String region;

    /**
     * AppID
     */
    @ApiModelProperty("AppID")
    private String appId;

    /**
     * Bucket前缀
     */
    @ApiModelProperty("Bucket前缀")
    private String bucketPrefix;

    /**
     * 文件名前缀策略,uuid,none
     */
    @ApiModelProperty("文件名前缀策略,uuid,none")
    private String prefixStrategy;

    /**
     * Bucket控制权限,
     */
    @ApiModelProperty("Bucket控制权限,")
    private String bucketAccessControl;

    /**
     * 是否默认
     */
    @ApiModelProperty("是否默认")
    private Integer defaultFlag;

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
