package org.yimon.tool.exception;

import org.yimon.tool.result.ReturnCode;

import java.io.Serial;

/**
 * @author: ym.gao
 * @description: 调用异常
 * @date: 2024/2/2 14:14
 */
public class InvokeException extends RuntimeException {

    /**
     * serialVersionUID
     */
    @Serial
    private static final long serialVersionUID = -1861292257466595071L;
    /**
     * 交易状态
     */
    private String status = ReturnCode.EXCEPTION.status();
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 标准错误信息
     */
    private String message;

    public InvokeException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public InvokeException(String status, Integer code, String message) {
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
