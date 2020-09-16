package io.github.thinkframework.generator.lang.reflect;

import io.github.thinkframework.generator.lang.Clazz;
import io.github.thinkframework.generator.lang.annotation.ClazzAnnotations;

/**
 * 装饰器模式
 * @author lixiaobin
 * @since 2017/3/24
 */
public class ClazzFieldRemarksDecroate<T extends ClazzField> implements ClazzField, RemakrsDecroate {
    private T member;

    private String remarks;

    public ClazzFieldRemarksDecroate(T member) {
        this.member = member;
    }

    public ClazzFieldRemarksDecroate(T member, String remarks) {
        this.member = member;
        this.remarks = remarks;
    }

    @Override
    public String getName() {
        return member.getName();
    }

    @Override
    public Clazz getType() {
        return member.getType();
    }

    @Override
    public ClazzAnnotations getAnnotations() {
        return member.getAnnotations();
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
