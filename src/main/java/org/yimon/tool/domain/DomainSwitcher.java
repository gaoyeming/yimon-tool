package org.yimon.tool.domain;


import org.yimon.tool.context.BaseContext;

/**
 * @author: ym.gao
 * @description: 事件域
 * @date: 2024/1/31 14:47
 */
public interface DomainSwitcher {

    BaseContext covertNext(BaseContext requestContext, BaseContext responseContext);
}
