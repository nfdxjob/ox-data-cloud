package org.dshubs.odc.workflow.common.exception;

import org.flowable.common.engine.api.FlowableException;

/**
 * Flowable异常处理类
 *
 * @author 湖南牛数商智信息科技有限公司
 */
public class FlowableNoPermissionException extends FlowableException {

    private static final long serialVersionUID = 1L;

    public FlowableNoPermissionException(String message) {
        super(message);
    }

    public FlowableNoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
