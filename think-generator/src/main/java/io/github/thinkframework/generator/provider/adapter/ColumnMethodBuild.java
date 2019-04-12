package io.github.thinkframework.generator.provider.adapter;

import io.github.thinkframework.generator.lang.Clazz;
import io.github.thinkframework.generator.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.lang.reflect.ClazzMethod;
import io.github.thinkframework.generator.lang.reflect.RemarksInvocationHandler;
import io.github.thinkframework.generator.lang.reflect.impl.ClazzMethodImpl;
import io.github.thinkframework.generator.sql.model.Column;
import io.github.thinkframework.generator.util.StringUtils;
import io.github.thinkframework.generator.util.TypesUtils;

import java.math.BigInteger;
import java.sql.Types;
import java.util.LinkedHashSet;
import java.util.Set;

public class ColumnMethodBuild{

    public static ClazzMethod buildMethod(Column column){

        String columnName = column.getColumnName();
        Class clazz = TypesUtils.dataType(column.getDataType());
        //TODO Id类型
        if(column.getDataType() == Types.BIGINT && column.getColumnName().toLowerCase().endsWith("id")){
            clazz = BigInteger.class;
        }
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
