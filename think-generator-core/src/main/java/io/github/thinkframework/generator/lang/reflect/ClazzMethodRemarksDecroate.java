package io.github.thinkframework.generator.lang.reflect;

import io.github.thinkframework.generator.lang.Clazz;
import io.github.thinkframework.generator.lang.annotation.ClazzAnnotations;

import java.util.Collection;

/**
 * 装饰器模式
 * @author lixiaobin
 * @since 2017/3/24
 */
public class ClazzMethodRemarksDecroate<T extends ClazzMethod> implements ClazzMethod, RemakrsDecroate {
    private T member;

    private String remarks;

    public ClazzMethodRemarksDecroate(T member) {
        this.member = member;
    }

    public ClazzMethodRemarksDecroate(T member, String remarks) {
        this.member = member;
        this.remarks = remarks;
    }

    @Override
    public String getName() {
        return member.getName();
    }

    @Override
    public Clazz getReturnType() {
        return member.getReturnType();
    }

    @Override
    public Collection<Clazz> getParameterTypes() {
        return member.getParameterTypes();
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
