package org.yimon.tool.keyword;

import org.junit.Test;

/**
 * @author: ym.gao
 * @description: volatile 可见性案例
 * 该程序当属性flag不用volatile修饰的时候，即使在某个线程将flag的值修改为false,这个程序依旧就处于无限死循环，
 * 当flag用volatile修饰的时候；在某个线程将flag的值修改为false的时候，该程序的主线程while程序就会跳出，这个就是可见性
 * @date: 2024/7/22 下午2:03
 */
public class VolatileVisibleTest {

//    private static boolean flag = true;
    private static volatile boolean flag = true;

    static class BizHandler implements Runnable {
        @Override
        public void run() {
            //睡眠3s
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = false;
            System.out.println(Thread.currentThread().getName() + "：flag 修改为false");
        }
    }

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new BizHandler(), "volatile-thread-" + i);
            t.start();
        }
        int i = 0;
        while (flag) {
            i++;
        }
        System.out.println("第" + i + "次结束循环");
    }
}
