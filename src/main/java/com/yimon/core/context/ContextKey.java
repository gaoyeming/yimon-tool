package com.yimon.core.context;

import com.yimon.core.pojo.ABasePojo;
import com.yimon.core.pojo.ABodyPojo;
import com.yimon.core.pojo.AFootPojo;
import com.yimon.core.pojo.AHeadPojo;

/**
 * @author: ym.gao
 * @description: 交易请求参数上下文
 * @date: 2023/12/1 9:54
 */
public class ContextKey extends ABasePojo {

    /**
     * example
     */
    public static final ContextKey DEFAULT = new ContextKey("default", AHeadPojo.class, ABodyPojo.class, AFootPojo.class);

    private String desc;

    private Class<?> headClazz;
    private Class<?> bodyClazz;
    private Class<?> footClazz;

    public ContextKey() {
    }

    public ContextKey(String desc, Class<?> bodyClazz) {
        this.desc = desc;
        this.headClazz = AHeadPojo.class;
        this.bodyClazz = bodyClazz;
        this.footClazz = AFootPojo.class;
    }

    public ContextKey(String desc, Class<?> headClazz, Class<?> bodyClazz) {
        this.desc = desc;
        this.headClazz = headClazz;
        this.bodyClazz = bodyClazz;
        this.footClazz = AFootPojo.class;
    }

    public ContextKey(String desc, Class<?> headClazz, Class<?> bodyClazz, Class<?> footClazz) {
        this.desc = desc;
        this.headClazz = headClazz;
        this.bodyClazz = bodyClazz;
        this.footClazz = footClazz;
    }


    public String getDesc() {
        return desc;
    }

    public Class<?> getHeadClazz() {
        return headClazz;
    }

    public Class<?> getBodyClazz() {
        return bodyClazz;
    }

    public Class<?> getFootClazz() {
        return footClazz;
    }
}
