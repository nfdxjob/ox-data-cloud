package org.dshubs.odc.core.exception;

import org.dshubs.odc.core.util.result.IError;

/**
 * 通用异常
 *
 * @author create by wangxian 2021/12/30
 */
public class CommonException extends RuntimeException {

    private final String code;

    private final String message;

    public CommonException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CommonException(IError error) {
        super(error.getMessage());
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
