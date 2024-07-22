package org.yimon.tool.keyword;

import org.junit.Test;

/**
 * @author: ym.gao
 * @description: volatile ����֤ԭ���԰���
 * ʹ��volatile�ؼ��ֽ������Σ�10000���߳̽����ۼӣ��޷��ﵽ10000Ԥ��ֵ
 * @date: 2024/7/22 ����2:40
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
        System.out.println("��" + i + "�ν���ѭ����count="+count);
    }
}
