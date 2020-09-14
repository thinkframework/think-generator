package io.github.thinkframework.generator.lang;

import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.lang.impl.ClazzImpl;

import javax.sql.DataSource;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

public class ClazzFactory {

    private Class source;

    private Clazz clazz;

    public ClazzFactory() {
    }

    public ClazzFactory(Class source)  {
        this.source = source;
        this.clazz = ClassConvert.convert(source);
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
