package org.dshubs.odc.infra.util;

/**
 * 全局统一请求错误提示
 *
 * @author wangxian 2021/3/15
 */
public enum GlobalErrorCodeEnum implements IErrorCode {
    /**
     * 成功
     */
    OK("ok", "操作成功"),

    /**
     * 生成代码模板未找到
     */
    GENERATE_CODE_TEMPLATE_NOT_FOUND("template_not_found","生成代码模板未找到"),
    GENERATE_CODE_TEMPLATE_PARSE_ERROR("template_not_found","生成代码模板未找到"),
    /**
     * 内部服务错误
     */
    INTERNAL_SERVER_ERROR("internal_server_error", "系统繁忙,请稍后再试");

    private final String code;

    private final String message;


    GlobalErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
