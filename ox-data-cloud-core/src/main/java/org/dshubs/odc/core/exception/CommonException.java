package org.dshubs.odc.core.exception;

/**
 * 通用异常
 *
 * @author create by wangxian 2021/12/30
 */
public class CommonException extends RuntimeException{

    private final String code;

    private final String message;

    public CommonException(String code,String message) {
        super(code);
        this.code = code;
        this.message = message;
    }

    @Override
    public String  getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
