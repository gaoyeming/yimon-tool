package com.yimon.core.result;

import com.yimon.core.pojo.ABasePojo;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: ym.gao
 * @description: 应用层级模块处理结果
 * @date: 2023/11/20 14:57
 */
public class ModelResult<T> extends ABasePojo {

    private ReturnCode code;
    private T result;
    private String msg;
    private Exception exception;

    public ModelResult(ReturnCode code) {
        this.code = code;
        this.result = null;
        this.msg = StringUtils.EMPTY;
        this.exception = null;
    }

    public ModelResult(ReturnCode code, T result) {
        this.code = code;
        this.result = result;
        this.msg = StringUtils.EMPTY;
        this.exception = null;
    }

    public ModelResult(ReturnCode code, String msg, Exception exception) {
        this.code = code;
        this.result = null;
        this.msg = msg;
        this.exception = exception;
    }

    public static <T> ModelResult<T> getSuccessResult() {
        return new ModelResult<>(ReturnCode.SUCCEED);
    }

    public static <T> ModelResult<T> getSuccessResult(T result) {
        return new ModelResult<>(ReturnCode.SUCCEED, result);
    }

    public static <T> ModelResult<T> getFailureResult() {
        return new ModelResult<>(ReturnCode.FAILURE);
    }

    public static <T> ModelResult<T> getFailureResult(Exception exception) {
        return new ModelResult<>(ReturnCode.FAILURE, "处理失败", exception);
    }

    public static <T> ModelResult<T> getExceptionResult() {
        return new ModelResult<>(ReturnCode.EXCEPTION);
    }

    public static <T> ModelResult<T> getExceptionResult(Exception exception) {
        return new ModelResult<>(ReturnCode.EXCEPTION, "处理异常", exception);
    }

    public boolean isSuccess() {
        return ReturnCode.SUCCEED == this.code;
    }

    public boolean isFailure() {
        return ReturnCode.FAILURE == this.code;
    }

    public boolean isException() {
        return ReturnCode.EXCEPTION == this.code;
    }

    public ReturnCode getCode() {
        return code;
    }

    public void setCode(ReturnCode code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
