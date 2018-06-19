package org.think.generator.lang;

import org.think.generator.lang.annotation.ClazzAnnotations;
import org.think.generator.lang.reflect.ClazzField;
import org.think.generator.lang.reflect.ClazzMethod;

import java.util.Collection;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public interface Clazz {
    public ClazzPackage getPackage();
    public String getName();
    public String getSimpleName();


    public Collection<ClazzField> getFields();

    public Collection<ClazzMethod> getMethods();

    public ClazzAnnotations getAnnotations();
}
