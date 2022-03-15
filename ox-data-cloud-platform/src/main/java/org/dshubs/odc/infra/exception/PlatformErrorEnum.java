package org.dshubs.odc.infra.exception;

import org.dshubs.odc.core.util.JsonUtils;
import org.dshubs.odc.core.util.result.IError;

/**
 * @author create by wangxian 2022/3/15
 */
public enum PlatformErrorEnum implements IError {
    /**
     * 员工已绑定用户
     */
    EMPLOYEE_BOUND_USER("EMPLOYEE_BOUND_USER", "员工已绑定用户"),
    EMPLOYEE_NOT_EXISTS("EMPLOYEE_NOT_EXISTS", "员工不存在");

    private final String code;

    private final String message;

    PlatformErrorEnum(String code, String message) {
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
