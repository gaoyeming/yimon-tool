package org.yimon.tool.domain.domain;


import org.yimon.tool.context.BaseContext;
import org.yimon.tool.domain.bo.Biz03Request;
import org.yimon.tool.domain.bo.Biz03Response;
import org.yimon.tool.domain.context.TestContext;
import org.yimon.tool.entrance.AEntrance;
import org.yimon.tool.log.BaseLogger;

/**
 * Created by jetty on 2019/7/31.
 */
public class Biz03Handler extends AEntrance {

    @Override
    protected BaseContext process(BaseContext requestBaseContext) throws Exception {
        Biz03Request request = requestBaseContext.getBody(TestContext.BIZ_03_REQUEST, Biz03Request.class);
        BaseLogger.info("业务3请求参数:{}", request);
        Biz03Response response = new Biz03Response();
        response.setBiz03_response("response biz03");
        return BaseContext.fill(TestContext.BIZ_03_RESPONSE, response);
    }
}