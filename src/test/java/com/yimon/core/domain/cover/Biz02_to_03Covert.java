package com.yimon.core.domain.cover;

import com.yimon.core.context.BaseContext;
import com.yimon.core.context.ContextKey;
import com.yimon.core.domain.DomainSwitcher;
import com.yimon.core.domain.bo.Biz02Request;
import com.yimon.core.domain.bo.Biz02Response;
import com.yimon.core.domain.bo.Biz03Request;
import com.yimon.core.log.BaseLogger;

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
        next.setBiz03_request(response.getBiz02_response());
        BaseLogger.info("covert new:{}", next);
        return BaseContext.fill(nextContextKey, next);
    }
}
