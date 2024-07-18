package org.yimon.tool.result;


import org.yimon.tool.pojo.ABasePojo;

/**
 * @author: ym.gao
 * @description: 程序模块返回码
 * @date: 2023/11/20 14:53
 */
public class ReturnCode extends ABasePojo {

    public static final ReturnCode SUCCEED = new ReturnCode("SUCCEED", 200, "成功");
    public static final ReturnCode FAILURE = new ReturnCode("FAILURE", 400, "失败");
    public static final ReturnCode EXCEPTION = new ReturnCode("EXCEPTION", 500, "异常");

    private String status;
    private Integer code;
    private String msg;

    private ReturnCode() {
    }

    public ReturnCode(String status, int code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public String status() {
        return status;
    }

    public Integer code() {
        return code;
    }

    public String msg() {
        return msg;
    }

}
