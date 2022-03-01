package org.dshubs.odc.core.util.result;

import org.dshubs.odc.core.util.JsonUtils;

/**
 * 公共错误编码
 *
 * @author create by wangxian 2022/2/28
 */
public enum CommonErrorEnum implements IError {
    /**
     * 内部服务错误
     */
    INTERNAL_SERVICE_ERROR("INTERNAL.SERVER.ERROR", "程序出现错误,请稍后重试");

    private final String code;

    private final String message;

    CommonErrorEnum(String code, String message) {
        this.code = code;
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

    @Override
    public String toString() {
        return JsonUtils.getInstance().toJson(this);
    }
}
