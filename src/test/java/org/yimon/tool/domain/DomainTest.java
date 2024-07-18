package org.yimon.tool.domain;


import org.junit.Test;
import org.yimon.tool.context.BaseContext;
import org.yimon.tool.domain.bo.Biz01Request;
import org.yimon.tool.domain.context.TestContext;
import org.yimon.tool.domain.cover.Biz01_to_02Covert;
import org.yimon.tool.domain.cover.Biz02_to_03Covert;
import org.yimon.tool.domain.domain.Biz01Handler;
import org.yimon.tool.domain.domain.Biz02Handler;
import org.yimon.tool.domain.domain.Biz03Handler;
import org.yimon.tool.entrance.AEntrance;

/**
 * @author: ym.gao
 * @description: TODO
 * @date: 2024/1/31 19:15
 */
public class DomainTest {

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


    @Test
    public void domain() throws Exception {
        DomainTest.init();
        Biz01Request request = new Biz01Request();
        request.setBiz01_request("request biz01");
        BaseContext baseContext = BaseContext.fill(TestContext.BIZ_01_REQUEST, request);
        DomainFactory.getDomainHandler("ONE").fire(TestContext.BIZ_01_REQUEST, baseContext);
    }
}
