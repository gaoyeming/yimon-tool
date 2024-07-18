package org.yimon.tool.limit.impl;

import org.yimon.tool.limit.IRateLimiter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: ym.gao
 * @description: 限流算法之令牌桶算法
 * 按照一定规则生成令牌，（不能超过最大值）；每次请求先去桶中获取令牌；如果拿到令牌则允许本次请求。否则拒绝
 * @date: 2024/7/18 下午3:36
 */
public class TokenBucketRateLimiter implements IRateLimiter {

    /**
     * 桶中允许最多的令牌（也就是最大的qps）
     */
    private final int maxTokens;

    /**
     * 桶中剩余的令牌（就也是同一秒基于前一次请求之后还允许的请求）
     */
    private final AtomicInteger availableTokens;
    /**
     * 上一次填充令牌的时间
     */
    private volatile long lastRefillTimeStamp;

    public TokenBucketRateLimiter(int maxTokens) {
        this.maxTokens = maxTokens;
        this.availableTokens = new AtomicInteger(maxTokens);
        this.lastRefillTimeStamp = System.currentTimeMillis();
    }

    @Override
    public boolean isAllow(int acquire) {
        if (acquire > this.maxTokens) {
            return false;
        }
        long now = System.currentTimeMillis();
        // 尝试根据时间重新填充令牌
        refill(now);

        int currentTokens = this.availableTokens.get();
        // 如果没有足够的令牌，则立即返回false，不阻塞
        if (currentTokens < acquire) {
            return false;
        }
        // 如果令牌数量足够，则使用CAS减少一个令牌
        return this.availableTokens.compareAndSet(currentTokens, currentTokens - acquire);
    }

    private void refill(long now) {
        //这里就是token的生成规则，后续根据业务情况自定义生成规则；（这里是没100毫秒向桶新增2个令牌）
        double rule = (double) (now - this.lastRefillTimeStamp) / 100 * 2;
        int tokensToAdd = rule > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) rule;
        //获取桶中剩余的令牌
        int preCount = this.availableTokens.get();
        int newTokenCount = Math.min(this.maxTokens, preCount + tokensToAdd);
        // 使用CAS更新令牌数量，如果失败则忽略（其他线程可能已经更新了）
        if (tokensToAdd > 0) {
            if (this.availableTokens.compareAndSet(preCount, newTokenCount)) {
                // 这里不需要纠结lastRefillTimeStamp 和 availableTokens更新的原子性
                // 因为lastRefillTimeStamp 记录的是上一次更新时间
                // 如果当前线程成功，那么就更新吧
                lastRefillTimeStamp = now;
            }
        }
    }
}
