package org.yimon.tool.limit;

import org.junit.Test;
import org.yimon.tool.async.ThreadAbortPolicy;
import org.yimon.tool.async.ThreadPoolExecutor;
import org.yimon.tool.limit.impl.CountRateLimiter;

/**
 * @author: ym.gao
 * @description: 计数器限流测试-1
 * @date: 2024/7/18 下午7:24
 */
public class CountRateLimiterTest {

    private final CountRateLimiter countRateLimiter = new CountRateLimiter(5);

    @Test
    public void allowTest() {
        try (ThreadPoolExecutor executorAbort = new ThreadPoolExecutor()) {
            executorAbort.newThreadPool("APPID", 5, 6, 10L, 500, new ThreadAbortPolicy());
            for (int i = 0; i < 50; i++) {
                executorAbort.submit(() -> {
                    if (countRateLimiter.isAllow(1)) {
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
