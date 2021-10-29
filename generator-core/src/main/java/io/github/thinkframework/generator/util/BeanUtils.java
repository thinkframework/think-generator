package io.github.thinkframework.generator.util;

import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class BeanUtils {

    /**
     * 返回一个map
     * @param obj
     * @return
     */
    public static Map describe(Object obj) {
        if (obj instanceof Map)
            return (Map) obj;
        Map map = new HashMap();
        PropertyDescriptor[] descriptors = getPropertyDescriptors(obj.getClass());
        for (int i = 0; i < descriptors.length; i++) {
            String name = descriptors[i].getName();
            Method readMethod = descriptors[i].getReadMethod();
            if (readMethod != null) {
                try {
                    long start = System.currentTimeMillis();
                    map.put(name, readMethod.invoke(obj, new Object[]{}));
                } catch (Exception e) {
                    throw new GeneratorRuntimeException("BEANUTIL", e);
                }
            }
        }
        return map;
    }

    public static PropertyDescriptor[] getPropertyDescriptors(Class beanClass) {
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(beanClass);
        } catch (IntrospectionException e) {
            return (new PropertyDescriptor[0]);
        }
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        if (descriptors == null) {
            descriptors = new PropertyDescriptor[0];
        }
        return descriptors;
    }
}
