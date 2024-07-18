package org.yimon.tool.lock;

/**
 * 设置锁值；实际操作锁
 */
@FunctionalInterface
public interface TryRelease {

    Boolean carry(String key, String value);

}
