package org.yimon.tool.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: ym.gao
 * @description: 域工厂
 * @date: 2024/2/1 14:03
 */
public class DomainFactory {

    private static final Map<String, DomainHandler> domainHandlerMap = new ConcurrentHashMap<>();

    private DomainFactory() {
    }


    public static void register(String key, DomainHandler domainHandler) {
        domainHandlerMap.put(key, domainHandler);
    }

    public static DomainHandler getDomainHandler(String key) {
        return domainHandlerMap.get(key);
    }
}
