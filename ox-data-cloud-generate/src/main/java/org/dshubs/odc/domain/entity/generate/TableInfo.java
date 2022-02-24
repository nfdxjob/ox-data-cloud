package org.dshubs.odc.domain.entity.generate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 表信息
 * @author wangxian 2021/3/16
 */
@Data
@ApiModel("表信息")
public class TableInfo {
    @ApiModelProperty("表名称")
    private String tableName;

    @ApiModelProperty("存储引擎")
    private String engine;

    @ApiModelProperty("备注")
    private String comment;

    @ApiModelProperty("更新时间")
    private Date createTime;
}
