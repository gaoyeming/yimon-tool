package org.yimon.tool.keyword;

import org.junit.Test;

/**
 * @author: ym.gao
 * @description: volatile �ɼ��԰���
 * �ó�������flag����volatile���ε�ʱ�򣬼�ʹ��ĳ���߳̽�flag��ֵ�޸�Ϊfalse,����������ɾʹ���������ѭ����
 * ��flag��volatile���ε�ʱ����ĳ���߳̽�flag��ֵ�޸�Ϊfalse��ʱ�򣬸ó�������߳�while����ͻ�������������ǿɼ���
 * @date: 2024/7/22 ����2:03
 */
public class VolatileVisibleTest {

//    private static boolean flag = true;
    private static volatile boolean flag = true;

    static class BizHandler implements Runnable {
        @Override
        public void run() {
            //˯��3s
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = false;
            System.out.println(Thread.currentThread().getName() + "��flag �޸�Ϊfalse");
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
        System.out.println("��" + i + "�ν���ѭ��");
    }
}
