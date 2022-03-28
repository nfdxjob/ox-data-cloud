package org.dshubs.odc.workflow.common.exception;

/**
 * 验证码错误异常类
 *
 * @author 湖南牛数商智信息科技有限公司
 */
public class CaptchaException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException(String msg)
    {
        super(msg);
    }
}
