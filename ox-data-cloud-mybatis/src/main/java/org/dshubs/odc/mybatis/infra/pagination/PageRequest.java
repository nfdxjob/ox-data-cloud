package org.dshubs.odc.mybatis.infra.pagination;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author create by wangxian 2022/2/28
 */
@Data
public class PageRequest {

    /**
     * 页码
     */
    private Long page;


    /**
     * 每页大小
     * Results per page (max 100)
     * Default: 30
     */
    private Long perPage;


    /**
     * 排序 sort=rank|asc,zip_code|desc
     */
    private String sort;
}
