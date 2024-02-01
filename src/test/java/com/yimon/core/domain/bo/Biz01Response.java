package com.yimon.core.domain.bo;

import com.yimon.core.pojo.ABodyPojo;

/**
 * @author: ym.gao
 * @description: 业务1请求参数
 * @date: 2024/2/1 14:13
 */
public class Biz01Response extends ABodyPojo {

    private String biz01_response;

    public String getBiz01_response() {
        return biz01_response;
    }

    public void setBiz01_response(String biz01_response) {
        this.biz01_response = biz01_response;
    }
}
