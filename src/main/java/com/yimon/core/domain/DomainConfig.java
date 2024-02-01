package com.yimon.core.domain;

import com.yimon.core.context.ContextKey;
import com.yimon.core.entrance.AEntrance;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ym.gao
 * @description: 域配置；主要目的连接BIZ
 * @date: 2024/2/1 11:34
 */
public class DomainConfig<E extends AEntrance> {

    private final Map<ContextKey, E> eventMap;

    private final Map<ContextKey, ContextKey> nextContextMap;

    private final Map<ContextKey, DomainSwitcher> switcherMap;

    private ContextKey currentContextKey;

    private E entrance;

    private ContextKey nextContextKey;

    private DomainSwitcher switcher;

    public DomainConfig() {
        eventMap = new HashMap<>(8);
        nextContextMap = new HashMap<>(8);
        switcherMap = new HashMap<>(8);
    }

    public DomainConfig<E> permit(ContextKey currentContextKey) {
        this.currentContextKey = currentContextKey;
        return this;
    }

    public DomainConfig<E> event(E entrance) {
        this.entrance = entrance;
        return this;
    }

    public DomainConfig<E> to(ContextKey nextContextKey) {
        this.nextContextKey = nextContextKey;
        return this;
    }

    public DomainConfig<E> covert(DomainSwitcher switcher) {
        this.switcher = switcher;
        return this;
    }


    public void build() {
        eventMap.put(currentContextKey, entrance);
        nextContextMap.put(currentContextKey, nextContextKey);
        switcherMap.put(currentContextKey, switcher);
        //将相关参数置为null；预防下一次build会覆盖
        this.currentContextKey = null;
        this.entrance = null;
        this.nextContextKey = null;
        this.switcher = null;
    }


    public E getEntrance(ContextKey currentContextKey) {
        return eventMap.get(currentContextKey);
    }

    public ContextKey getNextContextKey(ContextKey currentContextKey) {
        return nextContextMap.get(currentContextKey);
    }

    public DomainSwitcher getSwitcher(ContextKey currentContextKey) {
        return switcherMap.get(currentContextKey);
    }
}
