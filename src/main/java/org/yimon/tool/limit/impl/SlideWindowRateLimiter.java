package org.yimon.tool.limit.impl;

import org.yimon.tool.limit.IRateLimiter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: ym.gao
 * @description: 限流算法之滑动窗口算法
 * 滑动窗口就是将一秒拆分的更细，为了避免计数器中的临界问题，让限制更加平滑，将固定窗口中分割出多个小时间窗口，分别在每个小的时间窗口中记录访问次数，然后根据时间将窗口往前滑动并删除过期的小时间窗口。
 * 计数器算法，可以看做只有两个窗口，因此在两个窗口边界的时候会出现临界问题。而滑动窗口统计当前时间处于的1s内产生了多少次请求，避免了临界问题
 * 优点：实现相对简单，且没有计数器算法的临界问题
 * 缺点：无法应对短时间高并发（突刺现象），比如我在间歇性高流量请求，每一批次的请求，处于不同的窗口（图中的虚线窗口）
 * @date: 2024/7/18 下午3:00
 */
public class SlideWindowRateLimiter implements IRateLimiter {

    /**
     * 假设 把一秒分割为 5 份
     * |-----|-----|-----|-----|------|
     * 0   200ms 400ms 600ms 800ms  1000ms
     * 假设当前是10s余500ms 那么应该落到 400ms——600ms之间
     * 算法就是 10s余500ms%1s = 500ms，500ms/(1s/5) = 2 ==> 对应400ms——600ms
     * 这里需要注意 如果不是同一秒的值，那么不要统计进去，
     * 比如9s的时候400ms——600ms值是10，现在时间到10s余500ms了
     * 统计的时候不是10+1，而是1，因为都不同一秒了,因此滑动窗口的元素需要记下当前是第一秒的值
     */
    /**
     * AtomicIntegerArray 是一个数组，也就是滑动窗口
     * AtomicStampedReference中reference记录数量
     * AtomicStampedReference中stamp 表示当前是第几秒
     */
    private final AtomicReferenceArray<AtomicStampedReference<Integer>> slideWindow;
    /**
     * 请求一秒内允许的最大值
     */
    private final int max;

    /**
     * 一份是多少ms！
     */
    private final int intervalDuration;


    public SlideWindowRateLimiter(int max, int arrayLength) {
        this.max = max;
        this.slideWindow = new AtomicReferenceArray<>(arrayLength);
        // 将一秒分成指定份数后计算每份包含多少毫秒；这里可能存在没办法整除的情况，不是本文的主题，暂不做考虑
        this.intervalDuration = 1000 / arrayLength;
    }


    @Override
    public boolean isAllow(int acquire) {
        if (acquire > this.max) {
            return false;
        }

        while (true) {
            //  当前时间
            long currentMilliSeconds = System.currentTimeMillis();
            int currentSeconds = currentSeconds(currentMilliSeconds);
            // 对应在滑动窗口的位置
            int index = (int) (currentMilliSeconds % 1000 / this.intervalDuration);
            // 求和
            long preSum = sum(currentSeconds);
            // 超出限流
            if (preSum + acquire >= this.max) {
                return false;
            }
            // 获取当前位置的引用
            AtomicStampedReference<Integer> element = this.slideWindow.get(index);
            // 如果没有初始化
            if (Objects.isNull(element)) {
                if (this.slideWindow.compareAndSet(index, null, new AtomicStampedReference<>(acquire, currentSeconds))) {
                    return true;
                }
            }
            // 刷新一下，因为这时候maybe被其他线程初始化了
            element = this.slideWindow.get(index);
            // 是同一秒，那么+，如果不是那么覆盖
            int sourceSeconds = element.getStamp();
            int updateValue = sourceSeconds == currentSeconds ? element.getReference() + acquire : acquire;
            if (element.compareAndSet(element.getReference(), updateValue, sourceSeconds, currentSeconds)) {
                return true;
            }
        }
    }

    private int sum(int currentSeconds) {
        int sum = 0;
        for (int i = 0; i < this.slideWindow.length(); i++) {
            AtomicStampedReference<Integer> element = this.slideWindow.get(i);
            // 是同一秒的值才统计！
            if (Objects.nonNull(element) && element.getStamp() == currentSeconds
                    && Objects.nonNull(element.getReference())) {
                sum = element.getReference();
            }
        }
        return sum;
    }

    private int currentSeconds(long currentTimeMillis) {
        Instant instant = Instant.ofEpochMilli(currentTimeMillis);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.toLocalTime().toSecondOfDay();
    }
}
