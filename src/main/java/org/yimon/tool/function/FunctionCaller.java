package org.yimon.tool.function;


import org.yimon.tool.exception.InvokeException;
import org.yimon.tool.log.BaseLogger;
import org.yimon.tool.result.ReturnCode;
import org.yimon.tool.util.StringUtils;

/**
 * @author: ym.gao
 * @description: 功能调用
 * @date: 2024/1/4 10:11
 */
public class FunctionCaller {

    /**
     * @param serviceName     服务名
     * @param serviceMethod   服务方法
     * @param request         请求参数
     * @param functionInvoker 实际调用者
     * @param <T>             请求参数
     * @param <R>             响应参数
     * @return <T, R> R
     */
    public static <T, R> R call(String serviceName, String serviceMethod, T request, FunctionInvoker<T, R> functionInvoker, boolean withEx) {
        String msg = StringUtils.joinWith(StringUtils.SPACE, "call", serviceName, serviceMethod);
        try {
            BaseLogger.info("{} request:{}", msg, request);
            R response = functionInvoker.invoke(request);
            BaseLogger.info("{} response:{}", msg, response);
            return response;
        } catch (Exception e) {
            BaseLogger.error("{} exception:", msg, e);
            if (withEx) {
                throw new InvokeException(ReturnCode.EXCEPTION.code(), StringUtils.joinWith(StringUtils.SPACE, msg, "exception"));
            }
            return null;
        }
    }
}
