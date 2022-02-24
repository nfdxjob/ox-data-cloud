package org.dshubs.odc.domain.entity.generate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangxian 2021/3/16
 */
@Data
@ApiModel("表列信息")
public class TableColumn {

    @ApiModelProperty("表字段名字")
    private String columnName;

    @ApiModelProperty("表字段类型")
    private String dataType;

    @ApiModelProperty("表字段备注")
    private String comment;

    private String columnKey;

    @ApiModelProperty("字段类型")
    private String attrType;

    @ApiModelProperty("字段名字")
    private String attrName;

    /**
     * 是否忽略不渲染
     */
    @ApiModelProperty("是否忽略不渲染")
    private Boolean ignore = false;

}
