package org.yimon.tool.limit.impl;

import org.yimon.tool.limit.IRateLimiter;

import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.stream.IntStream;

/**
 * @author: ym.gao
 * @description: 限流算法之计数器算法-2
 * 相比于CountRateLimiter，这里的优点在于如果一瞬间有1000线程过来，就会减少线程的自旋量
 * 因为将计数拆分成100个数组，（虽然拆分更多会减少更多的自旋但是会影响计算总额的性能也是不可取）这样每个线程随机去取一个数组进行累加
 * 然后再计算数组总和，这个综合就是一秒内通过的请求量
 * @date: 2024/7/18 下午2:30
 */
public class BetterCountRateLimiter implements IRateLimiter {

    /**
     * 规定AtomicIntegerArray数组的大小为100
     */
    private static final int arraySize = 100;
    /**
     * 使用ThreadLocal记录当前线程随机分配到哪个数组去计数
     */
    private static final ThreadLocal<Integer> THREAD_RANDOM_INDEX = ThreadLocal.withInitial(() -> ThreadLocalRandom.current().nextInt(arraySize));
    /**
     * AtomicIntegerArray 是一个数组，里面有100个元素。数组内的所有元素之和表示这一秒内通过的请求
     * stamp 表示当前是第几秒
     */
    private final AtomicStampedReference<AtomicIntegerArray> flowCountHelper;
    /**
     * 请求一秒内允许的最大值
     */
    private final int max;

    public BetterCountRateLimiter(int max) {
        this.max = max;
        AtomicIntegerArray countArray = new AtomicIntegerArray(arraySize);
        this.flowCountHelper = new AtomicStampedReference<>(countArray, LocalTime.now().toSecondOfDay());
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
            //随机获取一个数组下标
            int currentThreadRandomIndex = THREAD_RANDOM_INDEX.get();
            // 不是同一秒 尝试new 一个全新的数组！
            if (currentSeconds != preSeconds) {
                AtomicIntegerArray newCountArray = new AtomicIntegerArray(arraySize);
                newCountArray.set(currentThreadRandomIndex, acquire);
                if (this.flowCountHelper.compareAndSet(this.flowCountHelper.getReference(), newCountArray, preSeconds, currentSeconds)) {
                    return true;
                }
            }
            // 是同一秒 or cas 失败 如果是cas失败，那么说明存在另外一个线程new了一个权限数组
            // 统计这一秒有多少请求量
            // 细节1 重新使用flowCountHelper.getReference()，因为如果是上面cas失败，那么这里的flowCountHelper.getReference()对应的AtomicIntegerArray被替换成新的了
            AtomicIntegerArray countArray = this.flowCountHelper.getReference();
            // 统计总数
            long preCount = IntStream.range(0, countArray.length()).map(countArray::get).sum();
            // 理论上 上面的for不会消耗太多时间
            // 不够需要的，那么false
            if (preCount + acquire > this.max) {
                return false;
            }

            // 在currentThreadRandomIndex的原值
            int sourceValue = countArray.get(currentThreadRandomIndex);
            // 细节2：使用的是【细节1】拿到的array 这时候不能重新flowCountHelper.getReference()，因为如果上面的for统计超过了一秒，那么这一次的请求会加到下一秒
            if (countArray.compareAndSet(currentThreadRandomIndex, sourceValue, sourceValue + acquire)) {
                // 弊端，这里true 不一定成功的限制了qps，因为上面的求和 与 这里的cas 不具备一致性，存在其他线程修改了的情况
                return true;
            }
            // 理论冲突的概率降低了，不需要 yield 吧
        }
    }
}
