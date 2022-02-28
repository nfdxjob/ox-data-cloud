package org.dshubs.odc.core.util.result;

/**
 *
 *
 * @author create by wangxian 2022/2/28
 */
public interface IError {

    /**
     * 错误码,建议系统编码加模块加具体业务
     * 如platform服务租户编码重复则以hpfm.tenant.code.exists
     *
     * @return String
     */
    String getCode();

    /**
     * 错误消息
     * @return String
     */

    String getMessage();
}
