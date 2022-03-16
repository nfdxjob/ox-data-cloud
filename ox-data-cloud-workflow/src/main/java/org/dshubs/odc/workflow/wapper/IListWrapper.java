package org.dshubs.odc.workflow.wapper;

import java.util.List;

/**
 * @Description： 结果包装类
 * @GithubAuthor : zhanglinfu2012
 * @Date: 2022-03-15 22:05
 * @Version: 1.0.0
 * @Copyright: 湖南牛数商智信息科技有限公司
 */
public interface IListWrapper {
    /**
     * 执行List转换封装
     *
     * @param list
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List execute(List list);
}
