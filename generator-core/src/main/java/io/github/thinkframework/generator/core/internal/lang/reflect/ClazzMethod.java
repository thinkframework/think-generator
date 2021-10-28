package io.github.thinkframework.generator.core.internal.lang.reflect;

import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.lang.annotation.ClazzAnnotations;

import java.util.Collection;

/**
 * @author hdhxby
 * @since 2017/3/24
 */
public interface ClazzMethod extends ClazzMember {
    Clazz getReturnType();

    Collection<Clazz> getParameterTypes();

    ClazzAnnotations getAnnotations();
}
