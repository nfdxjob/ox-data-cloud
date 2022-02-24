package org.dshubs.odc.infra.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一Json格式返回类
 *
 * @author wangxian 2021/3/15
 */
@Data
@ApiModel("全局统一JSON格式返回模型")
public class JsonResult<T> {
    @ApiModelProperty("主数据")
    private T data;

    @ApiModelProperty("编码,成功使用ok,其他则表示失败")
    private String code;

    @ApiModelProperty("消息")
    private String message;

    public static <T> JsonResult<T> ok() {
        return ok(null);
    }

    /**
     * 用于处理操作成功的请求返回封装
     *
     * @param data 返回主体数据
     * @param <T>  参数类型
     * @return JsonResult<T>
     */
    public static <T> JsonResult<T> ok(T data) {
        return build(GlobalErrorCodeEnum.OK, data);
    }

    public static <T> JsonResult<T> error() {
        return error(GlobalErrorCodeEnum.INTERNAL_SERVER_ERROR);
    }

    public static <T> JsonResult<T> error(IErrorCode errorCode) {
        return build(errorCode, null);
    }


    private static <T> JsonResult<T> build(IErrorCode errorCode, T data) {
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.setData(data);
        jsonResult.setCode(errorCode.getCode());
        jsonResult.setMessage(errorCode.getMessage());
        return jsonResult;
    }
}
