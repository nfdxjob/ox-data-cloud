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
    INTERNAL_SERVICE_ERROR("INTERNAL.SERVER.ERROR", "程序出现错误,请稍后重试"),
    /**
     * 获取分布式锁失败
     */
    GET_LOCK_FAILED("GET.LOCK.FAILED", "获取锁失败,请稍后重试"),
    /**
     * 数据版本不一致,更新失败
     */
    DATA_UPDATE_ERROR("DATA.UPDATE.ERROR", "数据版本不一致,请重新操作");

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
