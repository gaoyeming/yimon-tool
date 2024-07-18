package org.yimon.tool.limit.impl;

import org.yimon.tool.limit.IRateLimiter;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: ym.gao
 * @description: 限流算法之计数器算法-1
 * 当前简单的计数器限流方式，利用CAS保证了原子性且AtomicStampedReference保证了可见性
 * 但是有个缺点就是：如果一瞬间有1000线程过来，那么1个线程成功，那么999个线程就要继续自旋，导致浪费了很多cpu资源
 * 优化方式就是在最后使用Thread.yield()让出时间片，但是这里又可能会导致请求处理的rt变高
 * @date: 2024/7/18 下午1:59
 */
public class CountRateLimiter implements IRateLimiter {

    /**
     * Integer 表示这一秒内通过的请求,
     * stamp 表示当前是第几秒
     */
    private final AtomicStampedReference<Integer> flowCountHelper;

    /**
     * 请求一秒内允许的最大值
     */
    private final int max;

    public CountRateLimiter(int max) {
        this.max = max;
        this.flowCountHelper = new AtomicStampedReference<>(0, LocalTime.now().toSecondOfDay());
    }


    @Override
    public boolean isAllow(int acquire) {
        if (acquire > this.max) {
            return false;
        }
        while (true) {
            // 当前是第几秒
            int currentSeconds = LocalTime.now().toSecondOfDay();
            // 上一次统计是第几秒
            int preSeconds = this.flowCountHelper.getStamp();
            // 上一次的数量
            Integer preCount = this.flowCountHelper.getReference();
            // 是同一秒，超过了阈值，那么false
            if (preSeconds == currentSeconds & preCount + acquire > this.max) {
                return false;
            }
            // 不是同一秒，或者是同一秒没超过阈值，那么cas更新
            if (this.flowCountHelper.compareAndSet(preCount, preSeconds == currentSeconds ? preCount + acquire : acquire, preSeconds, currentSeconds)) {
                return true;
            }
            // 更新失败,，让出CPU时间片 继续自旋；缺点：可能会导致请求处理的rt变高（即请求处理的响应时间变长）
            Thread.yield();
        }
    }
}
