package com.yimon.core.domain.bo;

import com.yimon.core.pojo.ABodyPojo;

/**
 * @author: ym.gao
 * @description: 业务1请求参数
 * @date: 2024/2/1 14:13
 */
public class Biz03Response extends ABodyPojo {

    private String biz03_response;

    public String getBiz03_response() {
        return biz03_response;
    }

    public void setBiz03_response(String biz03_response) {
        this.biz03_response = biz03_response;
    }
}
