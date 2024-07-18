package org.yimon.tool.domain.domain;


import org.yimon.tool.context.BaseContext;
import org.yimon.tool.domain.bo.Biz01Request;
import org.yimon.tool.domain.bo.Biz01Response;
import org.yimon.tool.domain.context.TestContext;
import org.yimon.tool.entrance.AEntrance;
import org.yimon.tool.log.BaseLogger;

/**
 * Created by jetty on 2019/7/31.
 */
public class Biz01Handler extends AEntrance {

    @Override
    protected BaseContext process(BaseContext requestBaseContext) throws Exception {
        Biz01Request request = requestBaseContext.getBody(TestContext.BIZ_01_REQUEST, Biz01Request.class);
        BaseLogger.info("业务1请求参数:{}", request);
        Biz01Response response = new Biz01Response();
        response.setBiz01_response("response biz01");
        return BaseContext.fill(TestContext.BIZ_01_RESPONSE, response);
    }
}