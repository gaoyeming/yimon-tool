package com.yimon.core.domain.domain;

import com.yimon.core.context.BaseContext;
import com.yimon.core.domain.bo.Biz01Request;
import com.yimon.core.domain.bo.Biz01Response;
import com.yimon.core.domain.context.TestContext;
import com.yimon.core.entrance.AEntrance;
import com.yimon.core.log.BaseLogger;

/**
 * Created by jetty on 2019/7/31.
 */
public class Biz01Handler extends AEntrance {

    @Override
    protected BaseContext process(BaseContext requestBaseContext) throws Exception {
        Biz01Request request = requestBaseContext.getBody(TestContext.BIZ_01_REQUEST, Biz01Request.class);
        BaseLogger.info("业务1请求参数:{}", request);
        Biz01Response response = new Biz01Response();
        response.setBiz01_response("Biz01_response");
        return BaseContext.fill(TestContext.BIZ_01_RESPONSE, response);
    }
}