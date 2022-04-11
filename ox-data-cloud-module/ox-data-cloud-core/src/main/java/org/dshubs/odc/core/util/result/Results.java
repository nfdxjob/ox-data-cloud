package org.dshubs.odc.core.util.result;

import lombok.Data;
import org.dshubs.odc.core.infra.threadlocal.HttpRequestThreadLocal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 统一返回处理
 *
 * @author create by wangxian 2021/12/5
 */
public class Results {


    private Results() {
    }

    public static <T> ResponseEntity<T> success(T data) {
        return data == null ? success() : ResponseEntity.ok(data);
    }

    public static <T> ResponseEntity<T> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    public static <T> ResponseEntity<T> success() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public static <T> ResponseEntity<T> invalid() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<T> invalid(T data) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
    }

    public static ResponseEntity<IError> error() {
        return new ResponseEntity<>(new ErrorResult(CommonErrorEnum.INTERNAL_SERVICE_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<IError> error(IError error) {
        return new ResponseEntity<>(new ErrorResult(error), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<IError> error(final String code, final String message) {
        return new ResponseEntity<>(new ErrorResult(code, message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity<T> newResult(int code) {
        return ResponseEntity.status(HttpStatus.valueOf(code)).build();
    }

    public static <T> ResponseEntity<T> newResult(int code, T data) {
        return ResponseEntity.status(HttpStatus.valueOf(code)).body(data);
    }

    @Data
    public static class ErrorResult implements IError {

        private String code;

        private String message;

        private String requestId;


        public ErrorResult(String code, String message) {
            this.code = code;
            this.message = message;
            this.requestId = HttpRequestThreadLocal.getRequestId();
        }

        public ErrorResult(IError iError) {
            this.code = iError.getCode();
            this.message = iError.getMessage();
            this.requestId = HttpRequestThreadLocal.getRequestId();
        }


        public void setCode(String code) {
            this.code = code;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public String getMessage() {
            return this.message;
        }
    }

}
