package org.dshubs.odc.infra.util;

/**
 * @author wangxian 2021/3/15
 */
public interface IErrorCode {
    /**
     * 获取提示编码
     *
     * @return code
     */
    default String getCode() {
        return "ok";
    }

    /**
     * 获取提示消息
     *
     * @return message;
     */
    default String getMessage() {
        return "操作成功";
    }
}
