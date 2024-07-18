package org.yimon.tool.domain;


import org.junit.Test;
import org.yimon.tool.context.BaseContext;
import org.yimon.tool.domain.bo.Biz01Request;
import org.yimon.tool.domain.bo.Biz02Request;
import org.yimon.tool.domain.bo.Biz03Request;
import org.yimon.tool.domain.context.TestContext;
import org.yimon.tool.domain.domain.Biz01Handler;
import org.yimon.tool.domain.domain.Biz02Handler;
import org.yimon.tool.domain.domain.Biz03Handler;
import org.yimon.tool.entrance.AEntrance;

/**
 * @author: ym.gao
 * @description: Biz test
 * @date: 2024/2/1 14:27
 */
public class BizTest {

    @Test
    public void biz01Test() throws Exception {
        Biz01Request request = new Biz01Request();
        request.setBiz01_request("request biz01");
        BaseContext baseContext = BaseContext.fill(TestContext.BIZ_01_REQUEST, request);
        AEntrance entrance = new Biz01Handler();
        entrance.uniformIn(baseContext);
    }

    @Test
    public void biz02Test() throws Exception {
        Biz02Request request = new Biz02Request();
        request.setBiz02_request("request biz02");
        BaseContext baseContext = BaseContext.fill(TestContext.BIZ_02_REQUEST, request);
        AEntrance entrance = new Biz02Handler();
        entrance.uniformIn(baseContext);
    }

    @Test
    public void biz03Test() throws Exception {
        Biz03Request request = new Biz03Request();
        request.setBiz03_request("request biz03");
        BaseContext baseContext = BaseContext.fill(TestContext.BIZ_03_REQUEST, request);
        AEntrance entrance = new Biz03Handler();
        entrance.uniformIn(baseContext);
    }
}
