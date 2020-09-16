package io.github.thinkframework.generator.internal.lang.reflect;

import io.github.thinkframework.generator.internal.lang.Clazz;
import io.github.thinkframework.generator.internal.lang.annotation.ClazzAnnotations;

/**
 * java.reflect.Field
 *
 * @author lixiaobin
 * @since 2017/3/24
 */
public interface ClazzField extends ClazzMember {
    Clazz getType();

    ClazzAnnotations getAnnotations();
}
