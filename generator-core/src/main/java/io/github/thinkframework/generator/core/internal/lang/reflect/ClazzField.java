package io.github.thinkframework.generator.core.internal.lang.reflect;

import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.lang.annotation.ClazzAnnotations;

/**
 * java.reflect.Field
 *
 * @author hdhxby
 * @since 2017/3/24
 */
public interface ClazzField extends ClazzMember {
    Clazz getType();

    ClazzAnnotations getAnnotations();
}
