package org.yimon.tool.domain.cover;


import org.yimon.tool.context.BaseContext;
import org.yimon.tool.context.ContextKey;
import org.yimon.tool.domain.DomainSwitcher;
import org.yimon.tool.domain.bo.Biz02Request;
import org.yimon.tool.domain.bo.Biz02Response;
import org.yimon.tool.domain.bo.Biz03Request;
import org.yimon.tool.log.BaseLogger;

/**
 * @author: ym.gao
 * @description: TODO
 * @date: 2024/2/1 14:38
 */
public class Biz02_to_03Covert implements DomainSwitcher {

    private final ContextKey requestContextKey;
    private final ContextKey responseContextKey;
    private final ContextKey nextContextKey;

    public Biz02_to_03Covert(ContextKey requestContextKey, ContextKey responseContextKey, ContextKey nextContextKey) {
        this.requestContextKey = requestContextKey;
        this.responseContextKey = responseContextKey;
        this.nextContextKey = nextContextKey;
    }

    @Override
    public BaseContext covertNext(BaseContext requestContext, BaseContext responseContext) {
        Biz02Request request = requestContext.getBody(this.requestContextKey, Biz02Request.class);
        BaseLogger.info("covert request:{}", request);

        Biz02Response response = responseContext.getBody(this.responseContextKey, Biz02Response.class);
        BaseLogger.info("covert response:{}", response);


        Biz03Request next = new Biz03Request();
        next.setBiz03_request("request biz03");
        BaseLogger.info("covert new:{}", next);
        return BaseContext.fill(nextContextKey, next);
    }
}
