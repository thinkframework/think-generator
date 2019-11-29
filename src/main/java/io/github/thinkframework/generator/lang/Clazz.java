package io.github.thinkframework.generator.lang;

import io.github.thinkframework.generator.lang.annotation.ClazzAnnotations;
import io.github.thinkframework.generator.lang.reflect.ClazzField;
import io.github.thinkframework.generator.lang.reflect.ClazzMethod;

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
