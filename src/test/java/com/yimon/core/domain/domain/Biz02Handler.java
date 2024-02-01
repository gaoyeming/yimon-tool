package com.yimon.core.domain.domain;

import com.yimon.core.context.BaseContext;
import com.yimon.core.domain.bo.Biz02Request;
import com.yimon.core.domain.bo.Biz02Response;
import com.yimon.core.domain.context.TestContext;
import com.yimon.core.entrance.AEntrance;
import com.yimon.core.log.BaseLogger;

/**
 * Created by jetty on 2019/7/31.
 */
public class Biz02Handler extends AEntrance {

    @Override
    protected BaseContext process(BaseContext requestBaseContext) throws Exception {
        Biz02Request request = requestBaseContext.getBody(TestContext.BIZ_02_REQUEST, Biz02Request.class);
        BaseLogger.info("业务2请求参数:{}", request);
        Biz02Response response = new Biz02Response();
        response.setBiz02_response("Biz02_response");
        return BaseContext.fill(TestContext.BIZ_02_RESPONSE, response);
    }
}