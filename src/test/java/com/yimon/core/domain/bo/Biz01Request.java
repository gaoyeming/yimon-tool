package com.yimon.core.domain.bo;

import com.yimon.core.pojo.ABodyPojo;

/**
 * @author: ym.gao
 * @description: 业务1请求参数
 * @date: 2024/2/1 14:13
 */
public class Biz01Request extends ABodyPojo {

    private String biz01_request;

    public String getBiz01_request() {
        return biz01_request;
    }

    public void setBiz01_request(String biz01_request) {
        this.biz01_request = biz01_request;
    }
}
