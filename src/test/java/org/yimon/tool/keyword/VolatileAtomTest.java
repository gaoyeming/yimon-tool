package org.yimon.tool.keyword;

import org.junit.Test;

/**
 * @author: ym.gao
 * @description: volatile 不保证原子性案例
 * 使用volatile关键字进行修饰，10000个线程进行累加；无法达到10000预期值
 * @date: 2024/7/22 下午2:40
 */
public class VolatileAtomTest {
    private static volatile int count = 0;

    static class BizHandler implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            System.out.println(count);
        }
    }

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread(new VolatileAtomTest.BizHandler(), "volatile-thread-" + i);
            t.start();
        }
        int i = 0;
        while (count != 10000) {
            i++;
        }
        System.out.println("第" + i + "次结束循环：count="+count);
    }
}
