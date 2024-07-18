package org.yimon.tool.limit;

import org.junit.Test;
import org.yimon.tool.async.ThreadAbortPolicy;
import org.yimon.tool.async.ThreadPoolExecutor;
import org.yimon.tool.limit.impl.SlideWindowRateLimiter;

/**
 * @author: ym.gao
 * @description: 滑动窗口限流测试
 * @date: 2024/7/18 下午7:25
 */
public class SlideWindowRateLimiterTest {

    private final SlideWindowRateLimiter slideWindowRateLimiter = new SlideWindowRateLimiter(5, 5);

    @Test
    public void allowTest() {
        try (ThreadPoolExecutor executorAbort = new ThreadPoolExecutor()) {
            executorAbort.newThreadPool("APPID", 5, 6, 10L, 500, new ThreadAbortPolicy());
            for (int i = 0; i < 50; i++) {
                executorAbort.submit(() -> {
                    if (slideWindowRateLimiter.isAllow(1)) {
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
