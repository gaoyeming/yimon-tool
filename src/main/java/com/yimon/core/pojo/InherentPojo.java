package com.yimon.core.pojo;

/**
 * @author: ym.gao
 * @description: 共同属性
 * @date: 2023/12/1 15:04
 */
public class InherentPojo extends ABasePojo {

    private AHeadPojo headPojo;
    private ABodyPojo bodyPojo;
    private AFootPojo footPojo;

    public static InherentPojo newInherentAttribute() {
        return new InherentPojo();
    }

    public AHeadPojo getHeadPojo() {
        return headPojo;
    }

    public void setHeadPojo(AHeadPojo headPojo) {
        this.headPojo = headPojo;
    }

    public ABodyPojo getBodyPojo() {
        return bodyPojo;
    }

    public void setBodyPojo(ABodyPojo bodyPojo) {
        this.bodyPojo = bodyPojo;
    }

    public AFootPojo getFootPojo() {
        return footPojo;
    }

    public void setFootPojo(AFootPojo footPojo) {
        this.footPojo = footPojo;
    }
}
