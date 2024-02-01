package com.yimon.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * @author: ym.gao
 * @description: 日志工具类
 * @date: 2023/11/30 13:48
 */
public class BaseLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private BaseLogger() {
    }

    /**
     * info 日志打印
     **/
    public static void info(String msg) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(msg);
        }
    }

    public static void info(String format, Object arg) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(format, arg);
        }
    }

    public static void info(String format, Object arg1, Object arg2) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(format, arg1, arg2);
        }

    }

    public static void info(String format, Object... arguments) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(format, arguments);
        }
    }

    public static void info(String msg, Throwable t) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(msg, t);
        }
    }

    /**
     * warn 日志打印
     **/
    public static void warn(String msg) {
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn(msg);
        }
    }

    public static void warn(String format, Object arg) {
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn(format, arg);
        }
    }

    public static void warn(String format, Object arg1, Object arg2) {
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn(format, arg1, arg2);
        }
    }

    public static void warn(String format, Object... arguments) {
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn(format, arguments);
        }
    }

    public static void warn(String msg, Throwable t) {
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn(msg, t);
        }
    }

    /**
     * error 日志打印
     **/
    public static void error(String msg) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(msg);
        }
    }

    public static void error(String format, Object arg) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(format, arg);
        }
    }

    public static void error(String format, Object arg1, Object arg2) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(format, arg1, arg2);
        }
    }

    public static void error(String format, Object... arguments) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(format, arguments);
        }
    }

    public static void error(String msg, Throwable t) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(msg, t);
        }
    }

    /**
     * 根据级别打印日志
     **/
    public static void logWithLevel(LogLevel level, String msg) {
        if (level == LogLevel.INFO) {
            info(msg);
        } else if (level == LogLevel.WARN) {
            warn(msg);
        } else if (level == LogLevel.ERROR) {
            error(msg);
        }

    }

    public static void logWithLevel(LogLevel level, String format, Object arg) {
        if (level == LogLevel.INFO) {
            info(format, arg);
        } else if (level == LogLevel.WARN) {
            warn(format, arg);
        } else if (level == LogLevel.ERROR) {
            error(format, arg);
        }
    }

    public static void logWithLevel(LogLevel level, String format, Object arg1, Object arg2) {
        if (level == LogLevel.INFO) {
            info(format, arg1, arg2);
        } else if (level == LogLevel.WARN) {
            warn(format, arg1, arg2);
        } else if (level == LogLevel.ERROR) {
            error(format, arg1, arg2);
        }
    }

    public static void logWithLevel(LogLevel level, String format, Object... arguments) {
        if (level == LogLevel.INFO) {
            info(format, arguments);
        } else if (level == LogLevel.WARN) {
            warn(format, arguments);
        } else if (level == LogLevel.ERROR) {
            error(format, arguments);
        }
    }

    public static void logWithLevel(LogLevel level, String msg, Throwable t) {
        if (level == LogLevel.INFO) {
            info(msg, t);
        } else if (level == LogLevel.WARN) {
            warn(msg, t);
        } else if (level == LogLevel.ERROR) {
            error(msg, t);
        }
    }
}
