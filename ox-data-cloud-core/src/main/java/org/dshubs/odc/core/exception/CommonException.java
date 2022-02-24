package org.dshubs.odc.core.exception;

/**
 * 通用异常
 *
 * @author create by wangxian 2021/12/30
 */
public class CommonException extends RuntimeException{

    private final String code;

    private  final transient Object[] params;

    public CommonException(String code, Object ... params) {
        super(code);
        this.code = code;
        this.params = params;
    }

    public Object[] getParams() {
        return params;
    }

    public String getCode() {
        return code;
    }
}
