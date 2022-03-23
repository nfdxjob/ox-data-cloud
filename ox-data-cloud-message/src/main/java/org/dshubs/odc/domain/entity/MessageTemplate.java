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
 * 消息模板
 *
 * @author daisicheng 2022-03-21
 */
@Data
@ApiModel("消息模板模型")
@EqualsAndHashCode(callSuper=false)
@TableName("omsg_message_template")
public class MessageTemplate extends AuditEntity {


    /**
     * 模板ID,主键
     */
    @ApiModelProperty("模板ID,主键")
    @TableId
    private Long templateId;

    /**
     * 模板编码
     */
    @ApiModelProperty("模板编码")
    private String templateCode;

    /**
     * 模板名称
     */
    @ApiModelProperty("模板名称")
    private String templateName;

    /**
     * 模板标题
     */
    @ApiModelProperty("模板标题")
    private String templateTitle;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String templateDescription;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 编辑器类型,MARKDOWN,RICH_TEXT
     */
    @ApiModelProperty("编辑器类型,MARKDOWN,RICH_TEXT")
    private String editorType;

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
