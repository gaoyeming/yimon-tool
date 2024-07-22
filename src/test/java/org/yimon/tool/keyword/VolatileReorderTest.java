package org.yimon.tool.keyword;

import org.junit.Test;

/**
 * @author: ym.gao
 * @description: volatile ��ֹ��������
 * �ó�������a,b,x,y���Բ���volatile���ε�ʱ�������߳�A���߳�B���п��ܷ�����������ʱ��b,y����ֵ�п���ͬʱΪ0��ʱ��ͻ����ѭ����
 * ������a,b,x,y�ùؼ���volatile�������κ��ָֹ���������b=0;y=0������Ͳ����ܳ��֣����Գ���Ҳ�ͻ�һֱѭ����ȥ
 * @date: 2024/7/22 ����2:38
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
            }, "�߳�A");

            Thread threadB = new Thread(() -> {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                x = 1;
                y = a;
            }, "�߳�B");
            threadA.start();
            threadB.start();
            threadA.join();//���̵߳ȴ�threadAִ�����
            threadB.join();//���̵߳ȴ�threadBִ�����

            System.out.println("ѭ����" + whileCount + "��b=" + b + ",y=" + y);
        } while (b != 0 || y != 0);
    }
}
