package org.yimon.tool.async;


import org.junit.Test;
import org.yimon.tool.exception.RejectedException;
import org.yimon.tool.log.BaseLogger;

/**
 * @author: ym.gao
 * @description: 线程池测试
 * @date: 2024/1/3 16:46
 */
public class ThreadPoolExecutorTest {

    public static void doMethod(int i) {
        try {
            Thread.sleep(500L);
            BaseLogger.info("{}任务业务处理完成", i);
        } catch (InterruptedException e) {
            BaseLogger.info("{}任务业务处理异常", i);
        }
    }

    @Test
    public void threadPoolTest() {
        ThreadPoolExecutor executorAbort = new ThreadPoolExecutor();
        executorAbort.newThreadPool("APPID", 5, 6, 10L, 5, new ThreadAbortPolicy());
        for (int i = 0; i < 12; i++) {
            try {
                int finalI = i;
                executorAbort.execute(() -> doMethod(finalI));
            } catch (RejectedException e) {
                System.err.println(e.getMessage());
            }
        }
        executorAbort.shutdown();
        while (!executorAbort.isTerminated()) {

        }
        System.err.println("<---------->");
        ThreadPoolExecutor executorDiscard = new ThreadPoolExecutor();
        executorDiscard.newThreadPool("APPID", 5, 6, 10L, 5, new ThreadDiscardPolicy());
        for (int i = 0; i < 12; i++) {
            int finalI = i;
            executorDiscard.execute(() -> doMethod(finalI));
        }
        executorDiscard.shutdown();
    }
}
