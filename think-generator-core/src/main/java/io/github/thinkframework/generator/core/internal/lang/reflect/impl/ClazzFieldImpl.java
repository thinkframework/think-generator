package io.github.thinkframework.generator.core.internal.lang.reflect.impl;

import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.lang.annotation.ClazzAnnotation;
import io.github.thinkframework.generator.core.internal.lang.annotation.ClazzAnnotations;
import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzField;

/**
 * @author hdhxby
 * @since 2017/3/24
 */
public class ClazzFieldImpl extends ClazzMemberImpl implements ClazzField {
    protected Clazz type;
    private ClazzAnnotations annotations = new ClazzAnnotations();

    public ClazzFieldImpl() {

    }

    public ClazzFieldImpl(String name, Clazz type) {
        setName(name);
        setType(type);
    }

    @Override
    public Clazz getType() {
        return type;
    }

    public void setType(Clazz type) {
        this.type = type;
    }

    @Override
    public ClazzAnnotations getAnnotations() {
        return annotations;
    }

    public void setAnnotations(ClazzAnnotations annotations) {
        this.annotations = annotations;
    }

    public void addAnnotation(ClazzAnnotation annotation) {
        annotations.add(annotation);
    }

    public String toString() {
        return name;
    }

}
