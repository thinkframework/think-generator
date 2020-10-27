package io.github.thinkframework.generator.core.design.builder;

import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.internal.lang.reflect.impl.ClazzFieldRemarksDecorator;
import io.github.thinkframework.generator.core.internal.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzField;
import io.github.thinkframework.generator.core.internal.lang.reflect.impl.ClazzFieldImpl;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Column;
import io.github.thinkframework.generator.core.util.StringUtils;
import io.github.thinkframework.generator.core.util.TypesProxy;

/**
 * 通过表生成对应的类
 *
 * @author hdhxby
 */
class ColumnFieldBuilder {

    private GeneratorConfiguration generatorConfiguration;

    public ColumnFieldBuilder generatorConfiguration(GeneratorConfiguration generatorConfiguration){
        this.generatorConfiguration = generatorConfiguration;
        return this;
    }

    public ClazzField buildField(Column column) {
        String columnName = column.getColumnName();
        Class clazz = new TypesProxy(generatorConfiguration).dataType(column.getDataType());

        ClazzImpl classType = new ClazzImpl(clazz);

        String fieldName = StringUtils.fieldName(columnName);
        ClazzFieldImpl field = new ClazzFieldImpl();
        field.setType(classType);
        field.setName(fieldName);

        return new ClazzFieldRemarksDecorator(field,StringUtils.isNotEmpty(column.getRemarks()) ? column.getRemarks() : column.getColumnName());
    }
}
