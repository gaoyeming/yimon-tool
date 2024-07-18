package org.yimon.tool.lock;


import org.yimon.tool.exception.RejectedException;
import org.yimon.tool.result.ReturnCode;
import org.yimon.tool.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author: ym.gao
 * @description: credis实现分布式锁-- 待测试
 * @date: 2024/2/2 14:14
 */
public class DistributedLock implements Lock {

    /**
     * 标记每个线程ID
     **/
    private static final ThreadLocal<String> THREAD_ID = new ThreadLocal<>();
    private final String distributedKey;
    private final TryAcquire tryAcquire;
    private final TryRelease tryRelease;

    public DistributedLock(String distributedKey, TryAcquire tryAcquire, TryRelease tryRelease) {
        this.distributedKey = distributedKey;
        this.tryAcquire = tryAcquire;
        this.tryRelease = tryRelease;
    }

    @Override
    public boolean tryLock(long waitTime, long expireTime, TimeUnit unit) {
        long time = unit.toMillis(waitTime);
        long current = System.currentTimeMillis();

        String distributedValue = StringUtils.join("DistributedLock-ThreadId:", Thread.currentThread().threadId());
        THREAD_ID.set(distributedValue);

        // 获取锁
        Boolean isAcquire = tryAcquire.carry(distributedKey, THREAD_ID.get(), expireTime, unit);
        // lock acquired
        if (Boolean.TRUE.equals(isAcquire)) {
            return true;
        }

        time -= System.currentTimeMillis() - current;
        // 等待时间用完，获取锁失败
        if (time <= 0) {
            THREAD_ID.remove();
            return false;
        }
        // 自旋获取锁
        while (true) {
            long currentTime = System.currentTimeMillis();
            isAcquire = tryAcquire.carry(distributedKey, THREAD_ID.get(), expireTime, unit);
            // lock acquired
            if (Boolean.TRUE.equals(isAcquire)) {
                return true;
            }

            time -= System.currentTimeMillis() - currentTime;
            if (time <= 0) {
                THREAD_ID.remove();
                return false;
            }
        }
    }

    @Override
    public void lock(long expireTime, TimeUnit unit) {
        String distributedValue = StringUtils.join("DistributedLock-ThreadId:", Thread.currentThread().threadId());
        THREAD_ID.set(distributedValue);
        Boolean acquired;
        do {
            acquired = tryAcquire.carry(distributedKey, THREAD_ID.get(), expireTime, unit);
        } while (Boolean.TRUE.equals(acquired));
    }

    @Override
    public void unlock() {
        if (!Boolean.TRUE.equals(tryRelease.carry(distributedKey, THREAD_ID.get()))) {
            //解锁失败抛出异常
            throw new RejectedException(ReturnCode.EXCEPTION.code(), "attempt to unlock lock fail, the current thread phase does not match");
        }
    }
}
