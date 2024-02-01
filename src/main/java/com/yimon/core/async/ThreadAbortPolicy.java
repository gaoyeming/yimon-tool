package com.yimon.core.async;

import com.yimon.core.constant.DateFormat;
import com.yimon.core.exception.RejectedException;
import com.yimon.core.log.BaseLogger;
import com.yimon.core.result.ReturnCode;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: ym.gao
 * @description: 实现自定义拒绝策略--抛异常
 * @date: 2023/8/25 14:23
 */
public class ThreadAbortPolicy extends ThreadPoolExecutor.AbortPolicy {

    private static final String EXHAUSTED_FORMAT = "Current Executor is EXHAUSTED! Message : <Task is Abort>" + System.lineSeparator() +
            "Task: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d)," + System.lineSeparator() +
            "Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s), time: %s!";

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        String msg = String.format(EXHAUSTED_FORMAT,
                r.toString(), executor.getPoolSize(), executor.getActiveCount(), executor.getCorePoolSize(), executor.getMaximumPoolSize(), executor.getLargestPoolSize(),
                executor.getTaskCount(), executor.getCompletedTaskCount(), executor.isShutdown(), executor.isTerminated(), executor.isTerminating(),
                DateFormatUtils.format(new Date(), DateFormat.CHINESE_DATE_TIME_PATTERN));
        BaseLogger.warn(msg);
        throw new RejectedException(ReturnCode.FAILURE.code(), msg);
    }
}
