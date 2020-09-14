package io.github.thinkframework.generator.lang;

import io.github.thinkframework.generator.lang.Clazz;
import io.github.thinkframework.generator.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.lang.reflect.ClazzField;
import io.github.thinkframework.generator.lang.reflect.ClazzMethod;
import io.github.thinkframework.generator.lang.reflect.impl.ClazzFieldImpl;
import io.github.thinkframework.generator.lang.reflect.impl.ClazzMemberImpl;
import io.github.thinkframework.generator.lang.reflect.impl.ClazzMethodImpl;
import io.github.thinkframework.generator.sql.model.Table;
import io.github.thinkframework.generator.util.BeanUtils;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassConvert {

    public static Clazz convert(Class clazz) {
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
        return Stream.of(BeanUtils.getPropertyDescriptors(clazz))
            .map(propertyDescriptor -> {
                return new ClazzFieldImpl(propertyDescriptor.getName(), new ClazzImpl(null, null, null));
            }).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * 根据列生成方法信息
     *
     * @param clazz
     * @return 方法信息
     */
    private static Set<ClazzMethod> buildMethod(Class clazz) {
        return
            Stream.of(BeanUtils.getPropertyDescriptors(clazz))
                .flatMap(propertyDescriptor -> {
                    return Stream.of(new ClazzMethodImpl(), new ClazzMethodImpl());
                }).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
