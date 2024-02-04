package com.yimon.core.lock;

import java.util.concurrent.TimeUnit;

/**
 * 设置锁值；实际操作锁
 */
@FunctionalInterface
public interface TryAcquire {

    public abstract Boolean carry(String key, String value, long expireTime, TimeUnit unit);

}
