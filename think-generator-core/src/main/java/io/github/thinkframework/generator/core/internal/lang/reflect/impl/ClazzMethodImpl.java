package io.github.thinkframework.generator.core.internal.lang.reflect.impl;

import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.lang.annotation.ClazzAnnotation;
import io.github.thinkframework.generator.core.internal.lang.annotation.ClazzAnnotations;
import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzMethod;

import java.util.Collection;

/**
 * @author hdhxby
 * @since 2017/3/24
 */
public class ClazzMethodImpl extends ClazzMemberImpl implements ClazzMethod {
    private Clazz returnType;
    private Collection<Clazz> parameterTypes;
    private ClazzAnnotations annotations = new ClazzAnnotations();

    public ClazzMethodImpl() {

    }

    public ClazzMethodImpl(String name, Clazz returnType) {
        setName(name);
        setReturnType(returnType);
    }
    @Override
    public Clazz getReturnType() {
        return returnType;
    }

    public void setReturnType(Clazz returnType) {
        this.returnType = returnType;
    }

    @Override
    public Collection<Clazz> getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Collection<Clazz> parameterTypes) {
        this.parameterTypes = parameterTypes;
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
