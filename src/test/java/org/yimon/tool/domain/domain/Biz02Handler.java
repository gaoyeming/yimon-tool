package org.yimon.tool.domain.domain;


import org.yimon.tool.context.BaseContext;
import org.yimon.tool.domain.bo.Biz02Request;
import org.yimon.tool.domain.bo.Biz02Response;
import org.yimon.tool.domain.context.TestContext;
import org.yimon.tool.entrance.AEntrance;
import org.yimon.tool.log.BaseLogger;

/**
 * Created by jetty on 2019/7/31.
 */
public class Biz02Handler extends AEntrance {

    @Override
    protected BaseContext process(BaseContext requestBaseContext) throws Exception {
        Biz02Request request = requestBaseContext.getBody(TestContext.BIZ_02_REQUEST, Biz02Request.class);
        BaseLogger.info("业务2请求参数:{}", request);
        Biz02Response response = new Biz02Response();
        response.setBiz02_response("response biz02");
        return BaseContext.fill(TestContext.BIZ_02_RESPONSE, response);
    }
}