package org.yimon.tool.lock;

import java.util.concurrent.TimeUnit;

public interface Lock {

    /**
     * tryLock以阻塞等待waitTime时间的方式来尝试获取锁。获取成功则返回 true，反之 false
     *
     * @param waitTime   尝试获取锁的最大时间
     * @param expireTime 分布式锁的过期时间
     * @param unit       当前时间单位
     * @return boolean
     */
    boolean tryLock(long waitTime, long expireTime, TimeUnit unit);

    /**
     * lock一直尝试自旋阻塞等待获取分布式锁，直到获取成功为止。而tryLock只会阻塞等待waitTime时间。
     *
     * @param expireTime 分布式锁的过期时间
     * @param unit       当前时间单位
     */
    void lock(long expireTime, TimeUnit unit);

    /**
     * 解锁
     */
    void unlock();

}
