package com.yimon.core.domain;

import com.yimon.core.context.BaseContext;
import com.yimon.core.domain.bo.Biz03Request;
import com.yimon.core.domain.context.TestContext;

/**
 * @author: ym.gao
 * @description: TODO
 * @date: 2024/1/31 19:15
 */
public class DomainTest {

    public static void main(String[] args) throws Exception {
        DomainInit.init();
        Biz03Request request = new Biz03Request();
        request.setBiz03_request("start");
        BaseContext baseContext = BaseContext.fill(TestContext.BIZ_03_REQUEST, request);
        System.out.println(DomainFactory.getDomainHandler("ONE").fire(TestContext.BIZ_03_REQUEST, baseContext));
    }
}
