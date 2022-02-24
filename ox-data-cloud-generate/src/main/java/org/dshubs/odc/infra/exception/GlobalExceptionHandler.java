package org.dshubs.odc.infra.exception;

import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.infra.util.IErrorCode;
import org.dshubs.odc.infra.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wangxian 2021/3/15
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public JsonResult<IErrorCode> unknownExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return JsonResult.error();
    }

    @ExceptionHandler(value = ButterflyException.class)
    public JsonResult<IErrorCode> xhrExceptionHandler(ButterflyException e) {
        log.error(e.getMessage(), e);
        return JsonResult.error(e.getErrorCode());

    }
}
