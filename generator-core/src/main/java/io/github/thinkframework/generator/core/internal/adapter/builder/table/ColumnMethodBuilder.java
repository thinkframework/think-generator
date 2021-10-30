package io.github.thinkframework.generator.core.internal.adapter.builder.table;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzMethod;
import io.github.thinkframework.generator.core.internal.lang.reflect.impl.ClazzMethodImpl;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Column;
import io.github.thinkframework.generator.util.StringUtils;
import io.github.thinkframework.generator.util.TypesProxy;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 通过表生成对应的类
 *
 * @author hdhxby
 */
public class ColumnMethodBuilder {

    private GeneratorConfiguration generatorConfiguration;

    public ColumnMethodBuilder generatorConfiguration(GeneratorConfiguration generatorConfiguration){
        this.generatorConfiguration = generatorConfiguration;
        return this;
    }

    public ClazzMethod build(Column column) {

        String columnName = column.getColumnName();
        Class clazz = new TypesProxy(generatorConfiguration).dataType(column.getDataType());

        String methodName = StringUtils.lowerCamel(columnName);
        ClazzImpl classType = new ClazzImpl(clazz);

        ClazzMethodImpl method = new ClazzMethodImpl();
        method.setReturnType(classType);
        method.setName(methodName);
        Set<Clazz> parameterTypes = new LinkedHashSet<Clazz>();
        parameterTypes.add(classType);
        method.setParameterTypes(parameterTypes);

        return method;
    }

}
