package org.dshubs.odc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dshubs.odc.mybatis.domain.AuditEntity;

import java.time.LocalDateTime;

/**
 * 公告信息
 *
 * @author daisicheng 2022-03-21
 */
@Data
@ApiModel("公告信息模型")
@EqualsAndHashCode(callSuper = false)
@TableName("omsg_notice")
public class Notice extends AuditEntity {


    /**
     * 公告主键
     */
    @ApiModelProperty("公告主键")
    @TableId
    private Long noticeId;

    /**
     * 公告主题
     */
    @ApiModelProperty("公告主题")
    private String title;

    /**
     * 公告内容
     */
    @ApiModelProperty("公告内容")
    private String noticeBody;

    /**
     * 接收方类型 1.用户 2.角色 3.部门
     */
    @ApiModelProperty("接收方类型 1.用户 2.角色 3.部门")
    private Integer receiverTypeCode;

    /**
     * 公告编辑器类型,MARKDOWN,RICH_TEXT
     */
    @ApiModelProperty("公告编辑器类型,MARKDOWN,RICH_TEXT")
    private String noticeEditorType;

    /**
     * 接收方ID
     */
    @ApiModelProperty("接收方ID")
    private Long receiverSourceId;

    /**
     * 有效期从
     */
    @ApiModelProperty("有效期从")
    private LocalDateTime startDate;

    /**
     * 有效期至
     */
    @ApiModelProperty("有效期至")
    private LocalDateTime endDate;

    /**
     * 发布人
     */
    @ApiModelProperty("发布人")
    private Long publishedBy;

    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    private LocalDateTime publishedDate;

    /**
     * 悬浮公告标识
     */
    @ApiModelProperty("悬浮公告标识")
    private Integer stickyFlag;

    /**
     * 公告状态 1.待发布2.已发布3.已删除
     */
    @ApiModelProperty("公告状态 1.待发布2.已发布3.已删除")
    private Integer noticeStatus;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

}
