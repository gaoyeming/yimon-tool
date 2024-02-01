package com.yimon.core.log;

/**
 * @author: ym.gao
 * @description: 日志测试
 * @date: 2024/2/1 15:37
 */
public class BaseLoggerTest {

    public static void main(String[] args) {
        // 创建并初始化 LoggerContext
//        ContextInitializer initializer = new ContextInitializer();
//
//        // 设置 Logback 配置文件路径
//        String logbackConfigFilePath = "path/to/logback.xml";
//        System.setProperty("logback.configurationFile", logbackConfigFilePath);
//
//        // 重新加载 Logback 配置文件
//        initializer.autoConfigure();
//
//
//        System.setProperty("logback.configurationFile", "resources/logback.xml");
        BaseLogger.info("info");
        BaseLogger.warn("warn");
        BaseLogger.error("error");
    }
}
