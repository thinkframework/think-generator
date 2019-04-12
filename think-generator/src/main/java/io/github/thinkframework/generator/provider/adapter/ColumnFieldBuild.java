package io.github.thinkframework.generator.provider.adapter;

import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.lang.reflect.ClazzField;
import io.github.thinkframework.generator.lang.reflect.impl.ClazzFieldImpl;
import io.github.thinkframework.generator.sql.model.Column;
import io.github.thinkframework.generator.util.StringUtils;
import io.github.thinkframework.generator.util.TypesUtils;

import java.math.BigInteger;
import java.sql.Types;

public class ColumnFieldBuild {
    private ColumnFieldBuild(){

    }

    public static ClazzField buildField(Column column){
        String columnName = column.getColumnName();
        Class clazz = TypesUtils.dataType(column.getDataType());

        //TODO Id类型
        if(column.getDataType() == Types.BIGINT && column.getColumnName().toLowerCase().endsWith("id")){
            clazz = BigInteger.class;
        }

        ClazzImpl classType = new ClazzImpl(clazz);

        String fieldName = StringUtils.fieldName(columnName);
        ClazzFieldImpl field = new ClazzFieldImpl();
        field.setType(classType);
        field.setName(fieldName);
        return field;
    }

    private static String getProperty(String key,String defaultValue){
        return (String) GeneratorContext.get().getProperty(key,defaultValue);
    }
}
