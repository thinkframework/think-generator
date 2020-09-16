package io.github.thinkframework.generator.internal.lang;

import io.github.thinkframework.generator.internal.lang.annotation.ClazzAnnotations;
import io.github.thinkframework.generator.internal.lang.reflect.ClazzField;
import io.github.thinkframework.generator.internal.lang.reflect.ClazzMethod;

import java.util.Collection;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public interface Clazz {

    ClazzPackage getPackage();

    String getName();

    String getSimpleName();

    Collection<ClazzField> getFields();

    Collection<ClazzMethod> getMethods();

    ClazzAnnotations getAnnotations();
}
