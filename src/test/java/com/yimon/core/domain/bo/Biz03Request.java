package com.yimon.core.domain.bo;

import com.yimon.core.pojo.ABodyPojo;

/**
 * @author: ym.gao
 * @description: 业务1请求参数
 * @date: 2024/2/1 14:13
 */
public class Biz03Request extends ABodyPojo {

    private String biz03_request;

    public String getBiz03_request() {
        return biz03_request;
    }

    public void setBiz03_request(String biz03_request) {
        this.biz03_request = biz03_request;
    }
}
