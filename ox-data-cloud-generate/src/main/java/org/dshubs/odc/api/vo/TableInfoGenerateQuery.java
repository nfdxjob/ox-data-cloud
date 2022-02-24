package org.dshubs.odc.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangxian 2021/3/16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("表查询模型")
public class TableInfoGenerateQuery extends BasePageQuery {
    @ApiModelProperty("表名称")
    private String tableName;

    @ApiModelProperty("备注")
    private String comment;
}
