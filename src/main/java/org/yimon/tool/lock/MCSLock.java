package org.yimon.tool.lock;


import org.yimon.tool.log.BaseLogger;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: ym.gao
 * @description: MCS锁
 * @date: 2023/9/5 17:43
 */
public class MCSLock {
    /**
     * 当前节点
     * 每个线程拥有自己的副本用于记录自己线程中持有的CLH节点状态信息
     */
    private final ThreadLocal<MCSNode> curNode;
    /**
     * 最后一个队列种MCS节点的状态信息,指向最后一个申请锁的MCSNode
     * 【注意】这里用了java的原子系列之AtomicReference，能保证原子更新
     */
    private final AtomicReference<MCSNode> tailNode;

    public MCSLock() {
        // 初始化当前线程（即实例化锁的线程）的MCS节点，锁状态为false；next指针为null
        curNode = ThreadLocal.withInitial(MCSNode::new);
        // 初始化最后一个队列中MCS节点的状态信息
        tailNode = new AtomicReference<>();
    }

    public void lock() {
        MCSNode cur = curNode.get();
        MCSNode pred = tailNode.getAndSet(cur);
        int spinCount = 0;
        //类似的，preNode == null从tail中没获取到值标志没有线程占用锁
        if (pred != null) {
            //当前线程进行自旋
            cur.blocked = true;
            //获取前继节点的下个指针引用，也就是当前线程持有的锁的状态
            pred.next = cur;
            //在自己node的locked变量自旋
            while (cur.blocked) {
                spinCount++;
            }
        }
        BaseLogger.info("{} spin {} times get lock", Thread.currentThread().getName(), spinCount);
    }

    public void unLock() {
        MCSNode cur = curNode.get();
        //获取下个指针的引用
        MCSNode next = cur.next;
        int spinCount = 0;
        //如果有后继者，直接设置next.blocked = false通知后继者结束自旋.
        //如果有执行下面操作
        if (next == null) {
            //设置成功表示设置期间也没有后继者加入，设置失败表示有后继者加入
            if (tailNode.compareAndSet(cur, null)) {
                BaseLogger.info("{} none after node, direct release lock", Thread.currentThread().getName());
                //线程复用的情况下，解决当前节点循环引用的问题
                curNode.remove();
                return;
            }
            //同样的需要等待lock()中step1完成
            while (cur.next == null) {
                BaseLogger.info("{} exist after node, await after node join", Thread.currentThread().getName());
                spinCount++;
            }
        }
        cur.next.blocked = false;//通知下个节点，我已经释放了锁，你可以去那锁了
        BaseLogger.info("{} spin {} times release lock", Thread.currentThread().getName(), spinCount);
        //for GC
        cur.next = null;
        //线程复用的情况下，解决当前节点循环引用的问题
        curNode.remove();
    }

    /**
     * CLH锁节点
     */
    private static class MCSNode {
        /**
         * 持有后继者的引用
         */
        volatile MCSNode next = null;
        /**
         * 默认未锁定
         * true表示当前线程自旋阻塞，false表示成功获取锁
         */
        volatile boolean blocked = false;
    }
}
