package org.think.generator.lang.annotation;

import org.think.generator.lang.annotation.impl.ClazzAnnotationsImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 有序集合
 * @author lixiaobin
 * @since 2017/5/25.
 */
public class ClazzAnnotations {
    ClazzAnnotationsImpl clazzAnnotations = new ClazzAnnotationsImpl();

    public boolean add(ClazzAnnotation clazzAnnotation){
        return clazzAnnotations.add(clazzAnnotation);
    }

    @Override
    public String toString() {
        return clazzAnnotations.toString();
    }

}
