package com.yimon.core.log;

/**
 * @author: ym.gao
 * @description: 日志级别
 * TRACE < DEBUG < INFO < WARN < ERROR < FATAL，级别越高，表示的问题越严重。
 * @date: 2023/11/30 13:55
 */
public enum LogLevel {
    INFO,
    WARN,
    ERROR,
    NONE;
}
