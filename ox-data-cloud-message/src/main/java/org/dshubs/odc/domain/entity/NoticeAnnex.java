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
 * 公告附件
 *
 * @author daisicheng 2022-03-21
 */
@Data
@ApiModel("公告附件模型")
@EqualsAndHashCode(callSuper=false)
@TableName("omsg_notice_annex")
public class NoticeAnnex extends AuditEntity {


    /**
     * 附件主键
     */
    @ApiModelProperty("附件主键")
    @TableId
    private Long annexId;

    /**
     * 公告主键
     */
    @ApiModelProperty("公告主键")
    private Long noticeId;

    /**
     * 上传目录
     */
    @ApiModelProperty("上传目录")
    private String directory;

    /**
     * 文件地址
     */
    @ApiModelProperty("文件地址")
    private String fileUrl;

    /**
     * 文件类型
     */
    @ApiModelProperty("文件类型")
    private String fileType;

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
     * 文件目录
     */
    @ApiModelProperty("文件目录")
    private String bucketName;

    /**
     * 服务器编码
     */
    @ApiModelProperty("服务器编码")
    private String serverCode;

}
