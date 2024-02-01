package com.yimon.core.context;

import com.yimon.core.check.Validate;
import com.yimon.core.exception.ValidateException;
import com.yimon.core.log.BaseLogger;
import com.yimon.core.pojo.*;
import com.yimon.core.result.ReturnCode;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: ym.gao
 * @description: 基础请求参数上下文
 * @date: 2023/12/1 14:55
 */
public class BaseContext extends ABasePojo {

    private final Map<ContextKey, InherentPojo> context = new ConcurrentHashMap<>();

    public static BaseContext fill(ContextKey contextKey, ABodyPojo aBodyPojo) {
        BaseContext baseContext = BaseContext.newContext();
        baseContext.put(contextKey, aBodyPojo);
        return baseContext;
    }

    public static BaseContext fill(ContextKey contextKey, AHeadPojo aHeadPojo, ABodyPojo aBodyPojo) {
        BaseContext baseContext = BaseContext.newContext();
        baseContext.put(contextKey, aHeadPojo);
        baseContext.put(contextKey, aBodyPojo);
        return baseContext;
    }

    public static BaseContext fill(ContextKey contextKey, AHeadPojo aHeadPojo, ABodyPojo aBodyPojo, AFootPojo aFootPojo) {
        BaseContext baseContext = BaseContext.newContext();
        baseContext.put(contextKey, aHeadPojo);
        baseContext.put(contextKey, aBodyPojo);
        baseContext.put(contextKey, aFootPojo);
        return baseContext;
    }

    public static BaseContext newContext() {
        return new BaseContext();
    }

    public InherentPojo get(ContextKey key) {
        if (Objects.isNull(key)) {
            return null;
        }
        InherentPojo value = context.get(key);
        if (Objects.isNull(value)) {
            return null;
        }
        check(key, value);
        return value;
    }

    public <T> T getHead(ContextKey key, Class<T> classOfT) {
        if (Objects.isNull(key)) {
            return null;
        }
        InherentPojo value = context.get(key);
        if (Objects.isNull(value)) {
            return null;
        }
        check(key, value);
        return classOfT.cast(value.getHeadPojo());
    }

    public <T> T getBody(ContextKey key, Class<T> classOfT) {
        if (Objects.isNull(key)) {
            return null;
        }
        InherentPojo value = context.get(key);
        if (Objects.isNull(value)) {
            return null;
        }
        check(key, value);
        return classOfT.cast(value.getBodyPojo());
    }

    public <T> T getFoot(ContextKey key, Class<T> classOfT) {
        if (Objects.isNull(key)) {
            return null;
        }
        InherentPojo value = context.get(key);
        if (Objects.isNull(value)) {
            return null;
        }
        check(key, value);
        return classOfT.cast(value.getFootPojo());
    }

    public InherentPojo put(ContextKey key, InherentPojo value) {
        check(key, value);
        return context.put(key, value);
    }

    public InherentPojo put(ContextKey key, AHeadPojo head) {
        Validate.isNonNull(key, "key not be null");
        Validate.isNonNull(head, "head not be null");
        LockSupport.parkNanos(this, 100000000);//100毫秒
        InherentPojo value = context.get(key);
        if (value == null) {
            value = InherentPojo.newInherentAttribute();
        }
        value.setHeadPojo(head);
        LockSupport.unpark(Thread.currentThread());
        return context.put(key, value);
    }

    public InherentPojo put(ContextKey key, ABodyPojo body) {
        Validate.isNonNull(key, "key not be null");
        Validate.isNonNull(body, "body not be null");
        LockSupport.parkNanos(this, 100000000);//100毫秒
        InherentPojo value = context.get(key);
        if (value == null) {
            value = InherentPojo.newInherentAttribute();
        }
        value.setBodyPojo(body);
        LockSupport.unpark(Thread.currentThread());
        return context.put(key, value);
    }

    public InherentPojo put(ContextKey key, AFootPojo foot) {
        Validate.isNonNull(key, "key not be null");
        Validate.isNonNull(foot, "foot not be null");
        LockSupport.parkNanos(this, 100000000);//100毫秒
        InherentPojo value = context.get(key);
        if (value == null) {
            value = InherentPojo.newInherentAttribute();
        }
        value.setFootPojo(foot);
        LockSupport.unpark(Thread.currentThread());
        return context.put(key, value);
    }


    private void check(ContextKey key, InherentPojo value) {
        Validate.isNonNull(key, "key not be null");
        Validate.isNonNull(value, "value not be null");
        if (value.getHeadPojo() != null && !key.getHeadClazz().isAssignableFrom(value.getHeadPojo().getClass())) {
            String errMsg = String.format("head incorrect type in context, please check. key desc = %s, expected type = [%s], actual type = [%s].", key.getDesc(), value.getHeadPojo().getClass(), key.getHeadClazz());
            BaseLogger.error(errMsg);
            throw new ValidateException(ReturnCode.FAILURE.code(), errMsg);
        }
        if (value.getBodyPojo() != null && !key.getBodyClazz().isAssignableFrom(value.getBodyPojo().getClass())) {
            String errMsg = String.format("body incorrect type in context, please check. key desc = %s, expected type = [%s], actual type = [%s].", key.getDesc(), value.getBodyPojo().getClass(), key.getHeadClazz());
            BaseLogger.error(errMsg);
            throw new ValidateException(ReturnCode.FAILURE.code(), errMsg);
        }
        if (value.getFootPojo() != null && !key.getFootClazz().isAssignableFrom(value.getFootPojo().getClass())) {
            String errMsg = String.format("foot incorrect type in context, please check. key desc = %s, expected type = [%s], actual type = [%s].", key.getDesc(), value.getFootPojo().getClass(), key.getHeadClazz());
            BaseLogger.error(errMsg);
            throw new ValidateException(ReturnCode.FAILURE.code(), errMsg);
        }
    }
}
