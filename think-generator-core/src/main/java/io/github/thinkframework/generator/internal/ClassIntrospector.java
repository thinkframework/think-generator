package io.github.thinkframework.generator.internal;

import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.internal.lang.Clazz;
import io.github.thinkframework.generator.internal.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.internal.lang.reflect.ClazzField;
import io.github.thinkframework.generator.internal.lang.reflect.ClazzMethod;
import io.github.thinkframework.generator.internal.lang.reflect.impl.ClazzFieldImpl;
import io.github.thinkframework.generator.internal.lang.reflect.impl.ClazzMethodImpl;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 获取bean信息
 * @author hdhxby
 */
public class ClassIntrospector {

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
            return Stream.of(Introspector.getBeanInfo(clazz).getPropertyDescriptors())
                    .flatMap(propertyDescriptor -> Stream.of(new ClazzMethodImpl(propertyDescriptor.getName(), new ClazzImpl(null, null, null)),
                        new ClazzMethodImpl(propertyDescriptor.getName(), new ClazzImpl(null, null, null))))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        } catch (IntrospectionException e) {
            throw new GeneratorRuntimeException(e);
        }
    }
}
