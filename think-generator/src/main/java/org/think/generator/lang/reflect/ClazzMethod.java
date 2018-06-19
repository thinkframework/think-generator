package org.think.generator.lang.reflect;

import org.think.generator.lang.Clazz;
import org.think.generator.lang.annotation.ClazzAnnotation;
import org.think.generator.lang.annotation.ClazzAnnotations;

import java.util.Collection;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public interface ClazzMethod extends ClazzMember{
    Clazz getReturnType();
    Collection<Clazz> getParameterTypes();
    ClazzAnnotations getAnnotations();
}
