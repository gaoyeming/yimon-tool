package org.yimon.tool.async;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: ym.gao
 * @description: 自定义线程池
 * @date: 2023/8/25 14:15
 */
public class ThreadPoolExecutor implements ExecutorService {

    /**
     * 核心线程数
     */
    private static final int DEFAULT_CORE_POOL_SIZE = 8;//默认值
    /**
     * 最大线程数
     */
    private static final int DEFAULT_MAX_POOL_SIZE = 10;//默认值
    /**
     * 非核心线程的空闲时间
     */
    private static final long DEFAULT_KEEP_ALIVE_SECONDS = 60L;//默认值
    /**
     * 工作队列长度
     */
    private static final int DEFAULT_WORK_SIZE = 5000;//默认值
    private ExecutorService executorService;

    public void newAbortThreadPool(String feature) {
        this.newThreadPool(feature, DEFAULT_CORE_POOL_SIZE, DEFAULT_MAX_POOL_SIZE, DEFAULT_KEEP_ALIVE_SECONDS, DEFAULT_WORK_SIZE, new ThreadAbortPolicy());
    }

    public void newDiscardThreadPool(String feature) {
        this.newThreadPool(feature, DEFAULT_CORE_POOL_SIZE, DEFAULT_MAX_POOL_SIZE, DEFAULT_KEEP_ALIVE_SECONDS, DEFAULT_WORK_SIZE, new ThreadDiscardPolicy());
    }


    public void newThreadPool(String feature,
                              int corePoolSize,
                              int maxPoolSize,
                              long keepAliveTime,
                              int workSize, RejectedExecutionHandler rejectedExecutionHandler) {
        this.executorService = new java.util.concurrent.ThreadPoolExecutor(corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(workSize),
                new ThreadPoolFactory(feature),
                rejectedExecutionHandler);
    }

    @Override
    public void shutdown() {
        this.executorService.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return this.executorService.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return this.executorService.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return this.executorService.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.executorService.awaitTermination(timeout, unit);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return this.executorService.submit(task);
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return this.executorService.submit(task, result);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return this.executorService.submit(task);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return this.executorService.invokeAll(tasks);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return this.executorService.invokeAll(tasks, timeout, unit);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return this.executorService.invokeAny(tasks);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.executorService.invokeAny(tasks, timeout, unit);
    }

    @Override
    public void execute(Runnable command) {
        this.executorService.execute(command);
    }
}
