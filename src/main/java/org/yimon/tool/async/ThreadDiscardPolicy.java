package org.yimon.tool.async;

import org.yimon.tool.constant.DatePattern;
import org.yimon.tool.log.BaseLogger;
import org.yimon.tool.util.DateFormatUtils;

import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: ym.gao
 * @description: 实现自定义拒绝策略--丢弃
 * @date: 2023/8/25 14:23
 */
public class ThreadDiscardPolicy extends ThreadPoolExecutor.DiscardPolicy {

    private static final String EXHAUSTED_FORMAT = "Current Executor is EXHAUSTED! Message : <Task is Discard>" + System.lineSeparator() +
            "Task: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d)," + System.lineSeparator() +
            "Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s), time: %s!";

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        String msg = String.format(EXHAUSTED_FORMAT,
                r.toString(), executor.getPoolSize(), executor.getActiveCount(), executor.getCorePoolSize(), executor.getMaximumPoolSize(), executor.getLargestPoolSize(),
                executor.getTaskCount(), executor.getCompletedTaskCount(), executor.isShutdown(), executor.isTerminated(), executor.isTerminating(),
                DateFormatUtils.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN));
        BaseLogger.warn(msg);
    }
}
