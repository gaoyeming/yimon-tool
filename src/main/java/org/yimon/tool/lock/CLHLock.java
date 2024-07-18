package org.yimon.tool.lock;


import org.yimon.tool.log.BaseLogger;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: ym.gao
 * @description: CLH锁
 * @date: 2023/9/5 14:49
 */
public class CLHLock {
    /**
     * 当前节点
     * 每个线程拥有自己的副本用于记录自己线程中持有的CLH节点状态信息
     */
    private final ThreadLocal<CLHNode> curNode;
    /**
     * 前继节点(指向前一个线程中持有的CLH节点信息)
     * 每个线程拥有自己的副本用于记录自己线程中持有的CLH节点状态信息
     */
    private final ThreadLocal<CLHNode> predNode;
    /**
     * 最后一个队列种CLH节点的状态信息
     * 【注意】这里用了java的原子系列之AtomicReference，能保证原子更新
     */
    private final AtomicReference<CLHNode> tailNode;

    /**
     * 初始化几个节点信息
     */
    public CLHLock() {
        // 初始化当前线程（即实例化锁的线程）的CLH节点，锁状态为false；这个方法会为子线程创建副本
        curNode = ThreadLocal.withInitial(CLHNode::new);
        // 初始化前继节点，注意此时前，存储的是null
        predNode = new ThreadLocal<>();
        // 初始化最后一个队列种CLH节点的状态信息，原子类引用
        tailNode = new AtomicReference<>(curNode.get());
    }

    /**
     * 获取锁
     */
    public void lock() {
        // 取出当前线程ThreadLocal存储的当前节点
        // 子线程都是获取父线程初始化的值，locked状态为false。
        CLHNode cur = curNode.get();
        // 此时把lock状态置为true，表示一个有效状态，
        // 表示当前线程的锁为锁定状态，具体是否生效也需要看下面逻辑
        cur.locked = true;
        // 记录实时的节点信息（先get再set；先get就是获取了上个节点信息，再set就是把当前线程更新过的进行设值）
        // 【注意】在多线程并发情况下，这里通过AtomicReference类能防止并发问题
        // 【注意】这个会导致锁不能重入（不着重说明了）
        // 【注意】线程池复用的情况下如果同一个线程二次获取锁导致引用自己的节点；前面cur.locked = true;之后导致前置节点pred.locked也一直为true；那么就会一直获取不到锁了
        CLHNode pred = tailNode.getAndSet(cur);
        // 将刚获取的尾结点（前一线程的当前节点）付给当前线程的前继节点
        // 这里必须设值，不然下个线程进来也有可能不进行自旋（两个线程同时进入不设值则获取的前继节点状态都是false）
        //前继节点需要等待解锁进行改变
        predNode.set(pred);
        int spinCount = 0;
        //开始自旋，自旋前继节点（等待前继节点释放锁）；自旋结束后则表明当前线程的CLH节点状态生效
        while (predNode.get() != null && predNode.get().locked) {
            spinCount++;
        }
        BaseLogger.info("{} spin {} times get lock", Thread.currentThread().getName(), spinCount);
    }

    /**
     * 释放锁
     */
    public void unLock() {
        // 获取当前线程的当前节点
        CLHNode cur = curNode.get();
        // 进行解锁操作
        cur.locked = false;
        BaseLogger.info("{} release lock", Thread.currentThread().getName());
        //目的：如果使用线程池线程复用的情况，就会存在循环引用问题；同一个线程循环引用的情况就会有可能一直获取不到锁；
        curNode.remove();
//        CLHNode newCurNode = new CLHNode();
//        curNode.set(newCurNode);

        //当前节点会变成前置节点，后续垃圾回收会回收掉原先当前节点引用；节约内存
//        curNode.set(predNode.get());
    }

    /**
     * CLH锁节点
     */
    private static class CLHNode {
        /**
         * 锁状态:true表示锁定状态、false表示释放状态
         * 为了保证locked状态是线程间可见的，因此用volatile关键字修饰
         */
        volatile boolean locked = false;
    }
}
