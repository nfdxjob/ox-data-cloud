package org.dshubs.odc.workflow.wapper;

import java.util.List;

/**
 * 结果包装类
 * @author 湖南牛数商智信息科技有限公司
 */
public interface IListWrapper {
    /**
     * 执行List转换封装
     *
     * @param list list
     * @return list
     */
    public List execute(List list);
}
