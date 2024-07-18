package org.yimon.tool.limit;

import org.junit.Test;
import org.yimon.tool.async.ThreadAbortPolicy;
import org.yimon.tool.async.ThreadPoolExecutor;
import org.yimon.tool.limit.impl.BetterCountRateLimiter;

/**
 * @author: ym.gao
 * @description: 计数器限流测试-2
 * @date: 2024/7/18 下午7:21
 */
public class BetterCountRateLimiterTest {

    private final BetterCountRateLimiter betterCountRateLimiter = new BetterCountRateLimiter(5);

    @Test
    public void allowTest() {
        try (ThreadPoolExecutor executorAbort = new ThreadPoolExecutor()) {
            executorAbort.newThreadPool("APPID", 5, 6, 10L, 500, new ThreadAbortPolicy());
            for (int i = 0; i < 50; i++) {
                executorAbort.submit(() -> {
                    if (betterCountRateLimiter.isAllow(1)) {
                        System.out.println(Thread.currentThread().getName() + " 获得了许可，执行操作。");
                    } else {
                        System.err.println(Thread.currentThread().getName() + " 请求被拒绝。");
                    }
                });
            }
            executorAbort.shutdown();
            while (!executorAbort.isTerminated()) {

            }
        }
    }
}
