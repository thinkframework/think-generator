package org.think.generator.provider.adapter;

import org.think.generator.context.GeneratorContext;
import org.think.generator.lang.impl.ClazzImpl;
import org.think.generator.lang.annotation.*;
import org.think.generator.lang.reflect.ClazzField;
import org.think.generator.lang.reflect.impl.ClazzFieldImpl;
import org.think.generator.sql.model.Column;
import org.think.generator.sql.model.impl.ColumnImpl;
import org.think.generator.util.StringUtils;
import org.think.generator.util.TypesUtils;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColumnFieldBuild {
    private ColumnFieldBuild(){

    }

    public static ClazzField buildField(Column column){
        String columnName = column.getColumnName();
        Class clazz = TypesUtils.dataType(column.getDataType());
        ClazzImpl classType = new ClazzImpl(clazz);

        String fieldName = StringUtils.fieldName(columnName);
        ClazzFieldImpl field = new ClazzFieldImpl();
        field.setType(classType);
        field.setName(fieldName);
        return field;
    }

    private static String getProperty(String key,String defaultValue){
        return (String) GeneratorContext.getContext().getProperty(key,defaultValue);
    }
}
