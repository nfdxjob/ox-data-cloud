package org.dshubs.odc.workflow.wrapper;

import org.dshubs.odc.workflow.common.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 待办列表包装类
 *
 * @author 湖南牛数商智信息科技有限公司
 */
@Component
public class TaskTodoListWrapper implements IListWrapper {

    @Autowired
    private ResponseFactory responseFactory;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List execute(List list) {
        return responseFactory.createTaskResponseList(list);
    }
}
