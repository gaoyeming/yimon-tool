package org.yimon.tool.lock;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: ym.gao
 * @description: CLH锁测试
 * @date: 2023/9/5 14:51
 */
public class CLHLockTest {

    private static final CLHLock lock = new CLHLock();
    private static final ExecutorService executorService = new ThreadPoolExecutor(2, 2, 5,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));
    private static Integer count = 0;

    static class TaskTest implements Runnable {

        @Override
        public void run() {
            lock.lock();
            count++;
            lock.unLock();
        }
    }

    @Test
    public void clhTest(){
        for (int i = 0; i < 100; i++) {
            executorService.execute(new TaskTest());
        }
        executorService.shutdown();
        //进行阻塞
        while (!executorService.isTerminated()) {
        }
        System.out.println("count--->" + count);
    }
}
