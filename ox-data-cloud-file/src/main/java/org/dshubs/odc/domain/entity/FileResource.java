package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.mybatis.domain.AuditEntity;

import java.io.Serializable;

/**
 * 文件资源
 *
 * @author zhou 2022-04-06
 */
@Data
@ApiModel("文件资源模型")
@EqualsAndHashCode(callSuper=false)
@TableName("ofile_resource")
public class FileResource extends AuditEntity implements Serializable {


    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    @TableId
    private Long fileResourceId;

    /**
     * 文件地址
     */
    @ApiModelProperty("文件地址")
    private String fileUrl;

    /**
     * 文件Key
     */
    @ApiModelProperty("文件Key")
    private String fileKey;

    /**
     * 文件类型
     */
    @ApiModelProperty("文件类型")
    private String fileType;

    /**
     * 文件目录
     */
    @ApiModelProperty("文件目录")
    private String fileDirectory;

    /**
     * 文件名称
     */
    @ApiModelProperty("文件名称")
    private String fileName;

    /**
     * 文件大小
     */
    @ApiModelProperty("文件大小")
    private Long fileSize;

    /**
     * Bucket名称
     */
    @ApiModelProperty("Bucket名称")
    private String bucketName;

    /**
     * 存储代码
     */
    @ApiModelProperty("存储代码")
    private String storageCode;

    /**
     * 来源类型
     */
    @ApiModelProperty("来源类型")
    private String sourceType;

    /**
     * 文件Md5
     */
    @ApiModelProperty("文件Md5")
    private String fileMd5;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

}
