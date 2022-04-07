package org.dshubs.odc.mybatis.infra.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author create by wangxian 2022/2/28
 */
@Data
@AllArgsConstructor
public class PageData<T> {
    /**
     * 记录
     */
    private List<T> items;

    /**
     * 总数
     */
    private long count;
}
