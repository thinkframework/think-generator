package io.github.thinkframework.generator.internal.lang.reflect;

import io.github.thinkframework.generator.internal.lang.Clazz;
import io.github.thinkframework.generator.internal.lang.annotation.ClazzAnnotations;

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
