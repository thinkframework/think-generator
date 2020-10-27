package io.github.thinkframework.generator.core.design.builder;

import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.internal.lang.reflect.impl.ClazzMethodRemarksDecorator;
import io.github.thinkframework.generator.core.design.proxy.RemarksInvocationHandler;
import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzMethod;
import io.github.thinkframework.generator.core.internal.lang.reflect.impl.ClazzMethodImpl;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Column;
import io.github.thinkframework.generator.core.util.StringUtils;
import io.github.thinkframework.generator.core.util.TypesProxy;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 通过表生成对应的类
 *
 * @author hdhxby
 */
class ColumnMethodBuilder {

    private GeneratorConfiguration generatorConfiguration;

    public ColumnMethodBuilder generatorConfiguration(GeneratorConfiguration generatorConfiguration){
        this.generatorConfiguration = generatorConfiguration;
        return this;
    }

    public ClazzMethod buildMethod(Column column) {

        String columnName = column.getColumnName();
        Class clazz = new TypesProxy(generatorConfiguration).dataType(column.getDataType());

        String methodName = StringUtils.fieldName(columnName);
        ClazzImpl classType = new ClazzImpl(clazz);

        ClazzMethodImpl method = new ClazzMethodImpl();
        method.setReturnType(classType);
        method.setName(methodName);
        Set<Clazz> parameterTypes = new LinkedHashSet<Clazz>();
        parameterTypes.add(classType);
        method.setParameterTypes(parameterTypes);

        ClazzMethod proxy = (ClazzMethod) RemarksInvocationHandler.proxy(method,StringUtils.isNotEmpty(column.getRemarks()) ? column.getRemarks() : column.getColumnName());

        return new ClazzMethodRemarksDecorator(method,StringUtils.isNotEmpty(column.getRemarks()) ? column.getRemarks() : column.getColumnName());
    }
}
