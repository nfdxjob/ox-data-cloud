package org.dshubs.odc.infra.exception;

import org.dshubs.odc.infra.util.IErrorCode;

/**
 * @author wangxian 2021/3/17
 */
public class GenerateCodeException extends ButterflyException {

    public GenerateCodeException(IErrorCode errorCode) {
        super(errorCode);
    }
}
