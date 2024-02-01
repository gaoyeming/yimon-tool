package com.yimon.core.entrance;


import com.yimon.core.context.BaseContext;
import com.yimon.core.log.BaseLogger;

public abstract class AEntrance {


    /**
     * @param requestBaseContext 请求上下文
     * @return BaseContext 响应上下文
     * @throws Exception 发生异常
     */
    public BaseContext uniformIn(BaseContext requestBaseContext) throws Exception {
        BaseLogger.info("Request BaseContext:{}", requestBaseContext);//请求上下文
        BaseContext responseBaseContext = process(requestBaseContext);//业务逻辑处理
        BaseLogger.info("Response BaseContext:{}", responseBaseContext);//响应上下文
        return responseBaseContext;
    }


    /**
     * 业务逻辑处理
     *
     * @param requestBaseContext 请求上下文
     * @return BaseContext 响应上下文
     */
    protected abstract BaseContext process(BaseContext requestBaseContext) throws Exception;

}
