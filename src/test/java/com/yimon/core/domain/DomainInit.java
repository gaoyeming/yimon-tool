package com.yimon.core.domain;

import com.yimon.core.domain.context.TestContext;
import com.yimon.core.domain.cover.Biz01_to_02Covert;
import com.yimon.core.domain.cover.Biz02_to_03Covert;
import com.yimon.core.domain.domain.Biz01Handler;
import com.yimon.core.domain.domain.Biz02Handler;
import com.yimon.core.domain.domain.Biz03Handler;
import com.yimon.core.entrance.AEntrance;

/**
 * Created by jetty on 2019/7/31.
 */
public class DomainInit {

    //初始化状态机
    public static void init() {
        //支持多状态机 这里以请假为例，可以支持多种
        DomainFactory.register("ONE", buildOne());

    }

    private static DomainHandler buildOne() {
        DomainConfig<AEntrance> domainConfig = new DomainConfig<>();
        domainConfig.permit(TestContext.BIZ_01_REQUEST)
                .event(new Biz01Handler())
                .to(TestContext.BIZ_02_REQUEST)
                .covert(new Biz01_to_02Covert(TestContext.BIZ_01_REQUEST, TestContext.BIZ_01_RESPONSE, TestContext.BIZ_02_REQUEST))
                .build();
        domainConfig.permit(TestContext.BIZ_02_REQUEST)
                .event(new Biz02Handler())
                .to(TestContext.BIZ_03_REQUEST)
                .covert(new Biz02_to_03Covert(TestContext.BIZ_02_REQUEST, TestContext.BIZ_02_RESPONSE, TestContext.BIZ_03_REQUEST))
                .build();
        domainConfig.permit(TestContext.BIZ_03_REQUEST)
                .event(new Biz03Handler())
                .build();

        return new DomainHandler(domainConfig);
    }

}
