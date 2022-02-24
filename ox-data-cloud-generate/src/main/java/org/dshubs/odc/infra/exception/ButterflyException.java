package org.dshubs.odc.infra.exception;


import org.dshubs.odc.infra.util.IErrorCode;

/**
 * @author wangxian 2021/3/15
 */
public abstract class ButterflyException extends RuntimeException {
    private final IErrorCode errorCode;

    public ButterflyException(IErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public IErrorCode getErrorCode() {
        return this.errorCode;
    }
}
