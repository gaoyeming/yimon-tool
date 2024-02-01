package com.yimon.core.domain;

import com.yimon.core.context.BaseContext;
import com.yimon.core.domain.bo.Biz01Request;
import com.yimon.core.domain.context.TestContext;
import com.yimon.core.domain.domain.Biz01Handler;
import com.yimon.core.entrance.AEntrance;

/**
 * @author: ym.gao
 * @description: Biz test
 * @date: 2024/2/1 14:27
 */
public class BizTest {

    public static void main(String[] args) throws Exception {
        Biz01Request request = new Biz01Request();
        request.setBiz01_request("start");
        BaseContext baseContext = BaseContext.fill(TestContext.BIZ_01_REQUEST, request);
        AEntrance entrance = new Biz01Handler();
        System.out.println(entrance.uniformIn(baseContext));
    }
}
