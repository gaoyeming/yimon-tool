package org.yimon.tool.context;


import org.yimon.tool.check.Validate;
import org.yimon.tool.exception.ValidateException;
import org.yimon.tool.log.BaseLogger;
import org.yimon.tool.pojo.ABasePojo;
import org.yimon.tool.result.ReturnCode;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: ym.gao
 * @description: 基础请求参数上下文
 * @date: 2023/12/1 14:55
 */
public class BaseContext extends ABasePojo {

    private final Map<ContextKey, ABasePojo> context = new ConcurrentHashMap<>();

    public static BaseContext fill(ContextKey contextKey, ABasePojo aBodyPojo) {
        BaseContext baseContext = BaseContext.newContext();
        baseContext.put(contextKey, aBodyPojo);
        return baseContext;
    }

    public static BaseContext newContext() {
        return new BaseContext();
    }


    public <T> T getBody(ContextKey key, Class<T> classOfT) {
        if (Objects.isNull(key)) {
            return null;
        }
        ABasePojo value = context.get(key);
        if (Objects.isNull(value)) {
            return null;
        }
        check(key, value);
        return classOfT.cast(value);
    }

    public ABasePojo put(ContextKey key, ABasePojo value) {
        check(key, value);
        return context.put(key, value);
    }

    /**
     * context上下文check
     *
     * @param key   key
     * @param value value
     */
    private void check(ContextKey key, ABasePojo value) {
        Validate.isNonNull(key, "key not be null");
        Validate.isNonNull(value, "value not be null");
        if (!key.getContextClazz().isAssignableFrom(value.getClass())) {
            String errMsg = String.format("body incorrect type in context, please check. key desc = %s, expected type = [%s], actual type = [%s].", key.getDesc(), key.getContextClazz(), value.getClass());
            BaseLogger.error(errMsg);
            throw new ValidateException(ReturnCode.FAILURE.code(), errMsg);
        }
    }
}
