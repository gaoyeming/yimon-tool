package org.yimon.tool.limit.impl;

import org.yimon.tool.limit.IRateLimiter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: ym.gao
 * @description: 限流算法之漏桶算法
 * @date: 2024/7/18 下午4:20
 */
public class LeakyBucketRateLimiter implements IRateLimiter {

    /**
     * 当前桶中的请求数量, 也就是需要处理得请求
     */
    private final AtomicInteger bucketLevel;
    /**
     * 桶的容量，也就是最大得qps；超过则拒绝请求，未超过则加入桶中
     */
    private final int capacity;
    /**
     * 漏水速率，单位：请求/秒 我们可以精确到毫秒
     */
    private final int leakRate;
    /**
     * 上一次漏水的时间戳
     */
    private long lastLeakTime;

    public LeakyBucketRateLimiter(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.bucketLevel = new AtomicInteger(0);
        this.lastLeakTime = System.currentTimeMillis();
    }

    @Override
    public boolean isAllow(int acquire) {
        if (acquire > this.capacity) {
            return false;
        }
        // 获取当前时间
        long currentTime = System.currentTimeMillis();
        //流出时间
        long elapsedTime = currentTime - this.lastLeakTime;
        //计算流出的水量 = （当前时间 - 上次时间） * 出水速率
        int leaked = (int) elapsedTime / 1000 * this.leakRate;

        //只有有流出水才更新时间戳，不然会漏不出水
        if (leaked > 0) {
            //计算桶内水量 = 桶内当前水量 - 流出的水量
            int bucketCount = this.bucketLevel.get();
            int newLevel = Math.max(0, bucketCount - leaked);
            if (this.bucketLevel.compareAndSet(bucketCount, newLevel)) {
                //流出成功则更新上次漏水时间戳
                this.lastLeakTime = currentTime;
            }
        }
        //重新获取桶中得数量
        int bucketCount = this.bucketLevel.get();
        // 尝试将请求加入桶中, 重新获取桶中得数量，获取之后cas更新桶得数量
        return bucketCount < this.capacity && this.bucketLevel.compareAndSet(bucketCount, bucketCount + acquire);
    }
}
