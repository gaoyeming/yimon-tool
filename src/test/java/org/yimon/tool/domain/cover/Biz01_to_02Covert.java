package org.yimon.tool.domain.cover;


import org.yimon.tool.context.BaseContext;
import org.yimon.tool.context.ContextKey;
import org.yimon.tool.domain.DomainSwitcher;
import org.yimon.tool.domain.bo.Biz01Request;
import org.yimon.tool.domain.bo.Biz01Response;
import org.yimon.tool.domain.bo.Biz02Request;
import org.yimon.tool.log.BaseLogger;

/**
 * @author: ym.gao
 * @description: TODO
 * @date: 2024/2/1 14:38
 */
public class Biz01_to_02Covert implements DomainSwitcher {

    private final ContextKey requestContextKey;
    private final ContextKey responseContextKey;
    private final ContextKey nextContextKey;

    public Biz01_to_02Covert(ContextKey requestContextKey, ContextKey responseContextKey, ContextKey nextContextKey) {
        this.requestContextKey = requestContextKey;
        this.responseContextKey = responseContextKey;
        this.nextContextKey = nextContextKey;
    }

    @Override
    public BaseContext covertNext(BaseContext requestContext, BaseContext responseContext) {
        Biz01Request request = requestContext.getBody(this.requestContextKey, Biz01Request.class);
        BaseLogger.info("covert request:{}", request);

        Biz01Response response = responseContext.getBody(this.responseContextKey, Biz01Response.class);
        BaseLogger.info("covert response:{}", response);


        Biz02Request next = new Biz02Request();
        next.setBiz02_request("request biz02");
        BaseLogger.info("covert new:{}", next);
        return BaseContext.fill(nextContextKey, next);
    }
}
