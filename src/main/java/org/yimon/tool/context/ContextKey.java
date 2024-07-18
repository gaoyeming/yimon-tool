package org.yimon.tool.context;


import org.yimon.tool.pojo.ABasePojo;

/**
 * @author: ym.gao
 * @description: 交易请求参数上下文
 * @date: 2023/12/1 9:54
 */
public class ContextKey extends ABasePojo {

    /**
     * example
     */
    public static final ContextKey DEFAULT = new ContextKey("default", ABasePojo.class);

    private String desc;

    private Class<?> contextClazz;

    private ContextKey() {
    }

    public ContextKey(String desc, Class<?> contextClazz) {
        this.desc = desc;
        this.contextClazz = contextClazz;
    }

    public String getDesc() {
        return desc;
    }

    public Class<?> getContextClazz() {
        return contextClazz;
    }
}
