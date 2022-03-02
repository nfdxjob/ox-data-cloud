package org.dshubs.odc.domain.entity;

import org.dshubs.odc.core.domain.AuditEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
* 岗位
*
* @author wangxian 2022-03-02
*/
@Data
@ApiModel("岗位模型")
@EqualsAndHashCode(callSuper=false)
@TableName("opfm_position")
public class Position extends AuditEntity {


    /**
     * 
     */
    @ApiModelProperty("")
    private Long positionId;

    /**
     * 职位编码
     */
    @ApiModelProperty("职位编码")
    private String positionCode;

    /**
     * 职位名称
     */
    @ApiModelProperty("职位名称")
    private String positionName;

    /**
     * 所属组织
     */
    @ApiModelProperty("所属组织")
    private String orgCode;

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
