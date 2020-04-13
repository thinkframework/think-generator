package io.github.thinkframework.generator.lang.reflect;

import io.github.thinkframework.generator.lang.Clazz;
import io.github.thinkframework.generator.lang.annotation.ClazzAnnotations;

import java.util.Collection;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public interface ClazzMethod extends ClazzMember {
    Clazz getReturnType();

    Collection<Clazz> getParameterTypes();

    ClazzAnnotations getAnnotations();
}
