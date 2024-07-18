package org.yimon.tool.limit;

import org.junit.Test;
import org.yimon.tool.async.ThreadAbortPolicy;
import org.yimon.tool.async.ThreadPoolExecutor;
import org.yimon.tool.limit.impl.LeakyBucketRateLimiter;

/**
 * @author: ym.gao
 * @description: TODO
 * @date: 2024/7/18 ����5:46
 */
public class LeakyBucketRateLimiterTest {

    private final LeakyBucketRateLimiter leakyBucketRateLimiter = new LeakyBucketRateLimiter(20, 1);

    @Test
    public void allowTest() {
        try (ThreadPoolExecutor executorAbort = new ThreadPoolExecutor()) {
            executorAbort.newThreadPool("APPID", 5, 6, 10L, 500, new ThreadAbortPolicy());
            for (int i = 0; i < 50; i++) {
                executorAbort.submit(() -> {
                    if (leakyBucketRateLimiter.isAllow(1)) {
                        System.out.println(Thread.currentThread().getName() + " �������ɣ�ִ�в�����");
                    } else {
                        System.err.println(Thread.currentThread().getName() + " ���󱻾ܾ���");
                    }
                });
            }
            executorAbort.shutdown();
            while (!executorAbort.isTerminated()) {

            }
        }
    }
}
