package org.dshubs.odc.api.vo;

import lombok.Data;

import java.util.List;

/**
 * @author wangxian 2021/3/15
 */
@Data
public class PageData<T> {
    public List<T> list;

    private Long total;

    public PageData(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }
}
