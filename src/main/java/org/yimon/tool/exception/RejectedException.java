package org.yimon.tool.exception;


import org.yimon.tool.result.ReturnCode;

import java.io.Serial;

/**
 * @author yeming.gao
 * @description: 自定义异常-拒绝异常
 * @date: 2024/2/2 14:14
 */
public class RejectedException extends RuntimeException {
    /**
     * serialVersionUID
     */
    @Serial
    private static final long serialVersionUID = -1861292257466595072L;
    /**
     * 交易状态
     */
    private String status = ReturnCode.FAILURE.status();
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 交易描述
     */
    private String message;

    public RejectedException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RejectedException(String status, Integer code, String message) {
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