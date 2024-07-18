package org.yimon.tool.domain.context;


import org.yimon.tool.context.ContextKey;
import org.yimon.tool.domain.bo.*;

/**
 * @author: ym.gao
 * @description: 参数上下文
 * @date: 2023/12/1 16:19
 */
@SuppressWarnings("all")
public class TestContext {

    public static final ContextKey BIZ_01_REQUEST = new ContextKey("BIZ业务1请求", Biz01Request.class);
    public static final ContextKey BIZ_01_RESPONSE = new ContextKey("BIZ业务1响应", Biz01Response.class);
    public static final ContextKey BIZ_02_REQUEST = new ContextKey("BIZ业务2请求", Biz02Request.class);
    public static final ContextKey BIZ_02_RESPONSE = new ContextKey("BIZ业务2响应", Biz02Response.class);
    public static final ContextKey BIZ_03_REQUEST = new ContextKey("BIZ业务3请求", Biz03Request.class);
    public static final ContextKey BIZ_03_RESPONSE = new ContextKey("BIZ业务3响应", Biz03Response.class);

}
