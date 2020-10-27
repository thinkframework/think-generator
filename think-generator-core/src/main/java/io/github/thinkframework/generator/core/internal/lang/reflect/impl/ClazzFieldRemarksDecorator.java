package io.github.thinkframework.generator.core.internal.lang.reflect.impl;

import io.github.thinkframework.generator.core.design.decorator.RemakrsDecorator;
import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.lang.annotation.ClazzAnnotations;
import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzField;

/**
 * 装饰器模式
 * @author hdhxby
 * @since 2017/3/24
 */
public class ClazzFieldRemarksDecorator implements ClazzField, RemakrsDecorator {
    private ClazzField member;

    private String remarks;

    public ClazzFieldRemarksDecorator(ClazzField member) {
        this.member = member;
    }

    public ClazzFieldRemarksDecorator(ClazzField member, String remarks) {
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
