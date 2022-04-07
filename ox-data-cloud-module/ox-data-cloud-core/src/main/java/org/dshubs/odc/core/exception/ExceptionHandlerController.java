package org.dshubs.odc.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.dshubs.odc.core.util.result.IError;
import org.dshubs.odc.core.util.result.Results;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author create by wangxian 2022/2/28
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<IError> handlerException(Exception e) {
        log.error("Exception Handler", e);
        return Results.error();
    }

    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity<IError> handlerException(CommonException e) {
        log.error("Common Exception Handler", e);
        return Results.error(e.getCode(), e.getMessage());
    }
}
