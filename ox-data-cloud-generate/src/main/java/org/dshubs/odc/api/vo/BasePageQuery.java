package org.dshubs.odc.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页基础查询Query
 *
 * @author wangxian 2021/3/15
 */
@Data
public class BasePageQuery {
    @ApiModelProperty("分页页码")
    private Long page = 1L;

    @ApiModelProperty("分页大小")
    private Long size;

    /**
     * 排序方式
     */
    @ApiModelProperty("排序方式,默认Desc")
    private String orderType = "desc";

    /**
     * 排序字段
     */
    @ApiModelProperty("排序字段,默认UpdateDate")
    private String orderField = "id";

    public Long getPage() {
        if (page == null) {
            return 1L;
        }
        return page;
    }

    public Long getSize() {
        if (size == null) {
            return 20L;
        }
        if (size > 2000L) {
            return 2000L;
        }
        return size;
    }
}
