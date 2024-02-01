package com.yimon.core.exception;

import com.yimon.core.result.ReturnCode;

/**
 * @author yeming.gao
 * @Description: 自定义异常-参数校验异常
 * @date 2019/12/5 17:40
 */
public class ValidateException extends RuntimeException {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1861292257466595076L;
    /**
     * 交易状态
     */
    private String status = ReturnCode.FAILURE.status();
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 标准错误信息
     */
    private String message;

    public ValidateException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ValidateException(String status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}