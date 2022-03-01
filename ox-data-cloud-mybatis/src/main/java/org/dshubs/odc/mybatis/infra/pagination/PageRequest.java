package org.dshubs.odc.mybatis.infra.pagination;

import lombok.Data;

/**
 * @author create by wangxian 2022/2/28
 */
@Data
public class PageRequest {

    /**
     * 页码
     */
    private Long page = 1L;


    /**
     * 每页大小
     * Results per page (max 100)
     * Default: 30
     */
    private Long perPage = 30L;


    private static final Long MAX_PER_PAGE = 100L;

    /**
     * 排序 sort=rank,asc
     */
    private String [] sort;

    public Long getPerPage() {
        return perPage > MAX_PER_PAGE ? MAX_PER_PAGE : perPage;
    }
}
