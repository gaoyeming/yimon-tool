package org.yimon.tool.lock;

import java.util.concurrent.TimeUnit;

/**
 * 设置锁值；实际操作锁
 */
@FunctionalInterface
public interface TryAcquire {

    Boolean carry(String key, String value, long expireTime, TimeUnit unit);

}
