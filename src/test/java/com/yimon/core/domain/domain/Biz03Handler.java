package com.yimon.core.domain.domain;

import com.yimon.core.context.BaseContext;
import com.yimon.core.domain.bo.Biz03Request;
import com.yimon.core.domain.bo.Biz03Response;
import com.yimon.core.domain.context.TestContext;
import com.yimon.core.entrance.AEntrance;
import com.yimon.core.log.BaseLogger;

/**
 * Created by jetty on 2019/7/31.
 */
public class Biz03Handler extends AEntrance {

    @Override
    protected BaseContext process(BaseContext requestBaseContext) throws Exception {
        Biz03Request request = requestBaseContext.getBody(TestContext.BIZ_03_REQUEST, Biz03Request.class);
        BaseLogger.info("业务3请求参数:{}", request);
        Biz03Response response = new Biz03Response();
        response.setBiz03_response("Biz03_response");
        return BaseContext.fill(TestContext.BIZ_03_RESPONSE, response);
    }
}