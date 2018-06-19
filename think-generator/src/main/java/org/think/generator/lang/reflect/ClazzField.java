package org.think.generator.lang.reflect;

import org.think.generator.lang.Clazz;
import org.think.generator.lang.annotation.ClazzAnnotation;
import org.think.generator.lang.annotation.ClazzAnnotations;

import java.util.Collection;

/**
 * java.reflect.Field
 * @author lixiaobin
 * @since 2017/3/24
 */
public interface ClazzField extends ClazzMember{
    Clazz getType();
    ClazzAnnotations getAnnotations();
}
