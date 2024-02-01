package com.yimon.core.async;

import com.yimon.core.exception.RejectedException;
import com.yimon.core.log.BaseLogger;

/**
 * @author: ym.gao
 * @description: 线程池测试
 * @date: 2024/1/3 16:46
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
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
            try {
                int finalI = i;
                executorDiscard.execute(() -> doMethod(finalI));
            } catch (RejectedException e) {
                System.err.println(e.getMessage());
            }
        }
        executorDiscard.shutdown();
    }

    public static void doMethod(int i) {
        try {
            Thread.sleep(500L);
            BaseLogger.info("{}任务业务处理完成", i);
        } catch (InterruptedException e) {
            BaseLogger.info("{}任务业务处理异常", i);
        }
    }
}
