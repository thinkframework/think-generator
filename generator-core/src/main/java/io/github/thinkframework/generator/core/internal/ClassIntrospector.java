package io.github.thinkframework.generator.core.internal;

import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzField;
import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzMethod;
import io.github.thinkframework.generator.core.internal.lang.reflect.impl.ClazzFieldImpl;
import io.github.thinkframework.generator.core.internal.lang.reflect.impl.ClazzMethodImpl;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 获取bean信息
 * 根据class文件转换成clazz
 * @author hdhxby
 */
public class ClassIntrospector {

    /**
     * 转换成 clazz
     * @param clazz 类信息
     * @return
     */
    public static Clazz getClazz(Class clazz) {
        ClazzImpl clazzImpl = new ClazzImpl(clazz);
        clazzImpl.setFields(buildField(clazz));
        clazzImpl.setMethods(buildMethod(clazz));
        return clazzImpl;
    }

    /**
     * 根据列生成生成字段信息
     *
     * @param clazz
     * @return 字段信息
     */
    private static Set<ClazzField> buildField(Class clazz) {
        try {
            return Stream.of(Introspector.getBeanInfo(clazz).getPropertyDescriptors())
                .filter(propertyDescriptor -> {
                    try {
                        return clazz.getDeclaredField(propertyDescriptor.getName()) != null;
                    }catch (Exception e) {
                        return false;
                    }
                })
                .map(propertyDescriptor -> new ClazzFieldImpl(propertyDescriptor.getName(), new ClazzImpl(null, null, null)))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        } catch (IntrospectionException e) {
            throw new GeneratorRuntimeException(e);
        }
    }

    /**
     * 根据列生成方法信息
     *
     * @param clazz
     * @return 方法信息
     */
    private static Set<ClazzMethod> buildMethod(Class clazz) {
        try {
            return Stream.of(Introspector.getBeanInfo(clazz).getMethodDescriptors())
                .filter(methodDescriptor -> {
                    try {
                        return clazz.getDeclaredMethod(methodDescriptor.getMethod().getName(), methodDescriptor.getMethod().getParameterTypes()) != null;
                    }catch (Exception e) {
                        return false;
                    }
                })
                .map(propertyDescriptor -> new ClazzMethodImpl(propertyDescriptor.getName(), new ClazzImpl(null, null, null)))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        } catch (IntrospectionException e) {
            throw new GeneratorRuntimeException(e);
        }
    }
}
