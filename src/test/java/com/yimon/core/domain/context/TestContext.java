package com.yimon.core.domain.context;

import com.yimon.core.context.ContextKey;
import com.yimon.core.domain.bo.*;
import com.yimon.core.pojo.AFootPojo;
import com.yimon.core.pojo.AHeadPojo;

/**
 * @author: ym.gao
 * @description: 参数上下文
 * @date: 2023/12/1 16:19
 */
@SuppressWarnings("all")
public class TestContext extends ContextKey {

    public static final ContextKey BIZ_01_REQUEST = new ContextKey("BIZ业务1请求", AHeadPojo.class, Biz01Request.class, AFootPojo.class);
    public static final ContextKey BIZ_01_RESPONSE = new ContextKey("BIZ业务1响应", AHeadPojo.class, Biz01Response.class, AFootPojo.class);
    public static final ContextKey BIZ_02_REQUEST = new ContextKey("BIZ业务2请求", AHeadPojo.class, Biz02Request.class, AFootPojo.class);
    public static final ContextKey BIZ_02_RESPONSE = new ContextKey("BIZ业务2响应", AHeadPojo.class, Biz02Response.class, AFootPojo.class);
    public static final ContextKey BIZ_03_REQUEST = new ContextKey("BIZ业务3请求", AHeadPojo.class, Biz03Request.class, AFootPojo.class);
    public static final ContextKey BIZ_03_RESPONSE = new ContextKey("BIZ业务3响应", AHeadPojo.class, Biz03Response.class, AFootPojo.class);
}
