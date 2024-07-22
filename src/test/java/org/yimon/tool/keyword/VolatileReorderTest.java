package org.yimon.tool.keyword;

import org.junit.Test;

/**
 * @author: ym.gao
 * @description: volatile 防止重排序案例
 * 该程序当属性a,b,x,y属性不用volatile修饰的时候，其中线程A，线程B就有可能发生重排序；这时候b,y两个值有可能同时为0这时候就会打破循环；
 * 当属性a,b,x,y用关键字volatile进行修饰后禁止指令重排序后。b=0;y=0的情况就不可能出现，所以程序也就会一直循环下去
 * @date: 2024/7/22 下午2:38
 */
public class VolatileReorderTest {

//    private static int a = 0, b = 0;
//    private static int x = 0, y = 0;

    private static volatile int a = 0, b = 0;
    private static volatile int x = 0, y = 0;

    @Test
    public void test() throws InterruptedException {
        int whileCount = 0;
        do {
            whileCount++;
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            Thread threadA = new Thread(() -> {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                b = x;
            }, "线程A");

            Thread threadB = new Thread(() -> {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                x = 1;
                y = a;
            }, "线程B");
            threadA.start();
            threadB.start();
            threadA.join();//主线程等待threadA执行完毕
            threadB.join();//主线程等待threadB执行完毕

            System.out.println("循环第" + whileCount + "次b=" + b + ",y=" + y);
        } while (b != 0 || y != 0);
    }
}
