package com.yimon.core.async;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: ym.gao
 * @description: 自定义线程工厂
 * @date: 2023/8/25 14:16
 */
public class ThreadPoolFactory implements ThreadFactory {

    private final String namePrefix;

    private final ThreadGroup group;
    /**
     * 线程编号
     */
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public ThreadPoolFactory(String feature) {
        SecurityManager securityManager = System.getSecurityManager();
        group = Objects.nonNull(securityManager) ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = "ThreadFactory's " + feature + "-Worker-";
    }

    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + threadNumber.getAndIncrement();
        Thread thread = new Thread(group, task, name, 0);
        thread.setDaemon(false);
        thread.setPriority(Thread.NORM_PRIORITY);
        return thread;
    }
}
