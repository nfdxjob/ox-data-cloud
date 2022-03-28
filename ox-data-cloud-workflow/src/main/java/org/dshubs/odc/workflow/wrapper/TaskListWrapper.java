package org.dshubs.odc.workflow.wrapper;

import org.dshubs.odc.workflow.common.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author guodong
 * @date 2020年3月22日
 */
@Component
public class TaskListWrapper implements IListWrapper {

    @Autowired
    private ResponseFactory responseFactory;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List execute(List list) {
        return responseFactory.createTaskResponseList(list);
    }
}
