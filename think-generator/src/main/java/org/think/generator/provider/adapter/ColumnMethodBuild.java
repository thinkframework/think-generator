package org.think.generator.provider.adapter;

import org.think.generator.lang.Clazz;
import org.think.generator.lang.impl.ClazzImpl;
import org.think.generator.lang.reflect.ClazzField;
import org.think.generator.lang.reflect.ClazzMethod;
import org.think.generator.lang.reflect.RemarksInvocationHandler;
import org.think.generator.lang.reflect.impl.ClazzMethodImpl;
import org.think.generator.sql.model.Column;
import org.think.generator.util.StringUtils;
import org.think.generator.util.TypesUtils;

import java.util.LinkedHashSet;
import java.util.Set;

public class ColumnMethodBuild{

    public static ClazzMethod buildMethod(Column column){

        String columnName = column.getColumnName();
        Class clazz = TypesUtils.dataType(column.getDataType());
        String methodName = StringUtils.fieldName(columnName);
        ClazzImpl classType = new ClazzImpl(clazz);

        ClazzMethodImpl method = new ClazzMethodImpl();
        method.setReturnType(classType);
        method.setName(methodName);
        Set<Clazz> parameterTypes = new LinkedHashSet<Clazz>();
        parameterTypes.add(classType);
        method.setParameterTypes(parameterTypes);

        ClazzMethod proxy = (ClazzMethod) RemarksInvocationHandler.proxy(method, StringUtils.isNotEmpty(column.getRemarks()) ? column.getRemarks() : column.getColumnName());

        return proxy;
    }

}
