package org.think.generator.lang.reflect;

import org.think.generator.lang.Clazz;
import org.think.generator.lang.annotation.ClazzAnnotations;

import java.util.Collection;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class ClazzMethodDecorate implements ClazzMethod{
    ClazzMethod cl;


    @Override
    public String getName() {
        return null;
    }

    public Clazz getReturnType(){
        return cl.getReturnType();
    }
    public Collection<Clazz> getParameterTypes(){
        return cl.getParameterTypes();
    }
    public ClazzAnnotations getAnnotations(){
        return  cl.getAnnotations();
    }

    public String getFkString(){
        return "";
    }
}
