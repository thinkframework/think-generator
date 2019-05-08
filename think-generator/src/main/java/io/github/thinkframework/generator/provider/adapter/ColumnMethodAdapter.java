package io.github.thinkframework.generator.provider.adapter;

import io.github.thinkframework.generator.lang.Clazz;
import io.github.thinkframework.generator.lang.annotation.ClazzAnnotations;
import io.github.thinkframework.generator.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.lang.reflect.ClazzMethod;
import io.github.thinkframework.generator.lang.reflect.RemarksInvocationHandler;
import io.github.thinkframework.generator.lang.reflect.impl.ClazzMethodImpl;
import io.github.thinkframework.generator.sql.model.Column;
import io.github.thinkframework.generator.sql.model.ImportedKey;
import io.github.thinkframework.generator.sql.model.impl.ColumnImpl;
import io.github.thinkframework.generator.util.StringUtils;
import io.github.thinkframework.generator.util.TypesUtils;

import java.math.BigInteger;
import java.sql.Types;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 适配数据库的Column和Java字段
 * @author lixiaobin
 * @since 2017/5/16.
 */
public class ColumnMethodAdapter implements ClazzMethod,Column {
    private ClazzMethod clazzMehod;
    private Column column;
    private boolean columnField;
    private boolean ignore;
    private String typeScript;
    public ColumnMethodAdapter(Column column){
        this.column = column;
        clazzMehod = buildMethod(column);
        columnField = true;
    }

    public ColumnMethodAdapter(ClazzMethod clazzMehod){
        this.clazzMehod = clazzMehod;
        column = new ColumnImpl();
    }


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

    @Override
    public String getName() {
        return clazzMehod.getName();
    }

    @Override
    public Clazz getReturnType(){
        return clazzMehod.getReturnType();
    }
    
    @Override
    public Collection<Clazz> getParameterTypes(){
        return clazzMehod.getParameterTypes();
    }

    @Override
    public ClazzAnnotations getAnnotations() {
        return clazzMehod.getAnnotations();
    }

    @Override
    public String getColumnName() {
        return column.getColumnName();
    }

    @Override
    public String getRemarks() {
        return column.getRemarks();
    }

    @Override
    public int getDataType() {
        return column.getDataType();
    }

    @Override
    public boolean getPrimaryKey() {
        return column.getPrimaryKey();
    }


    public boolean getExportedKey() {
        return ((ColumnImpl)column).isExportedKey();
    }

    public boolean getIsImportedKey() {
        return ((ColumnImpl)column).getIsImportedKey();
    }

    public ImportedKey getImportedKey() {
        return ((ColumnImpl)column).getImportedKey();
    }

    @Override
    public String getIsNullable() {
        return column.getIsNullable();
    }

    @Override
    public int getColumnSize() {
        return column.getColumnSize();
    }

    public boolean getUnique() {
        return false;
    }

    public boolean getColumnField() {
        return columnField;
    }

    /**
     * 设置是否忽略当前字段
     * @return 是否忽略
     */
    public boolean isIgnore() {
        return ignore;
    }

    /**
     * 设置是否忽略当前字段
     * @param ignore 是否忽略
     */
    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

    /**
     * 获取TypeScript类型.
     * @return TypeScript类型
     */
    public String getTypeScript() {
        return typeScript;
    }

    /**
     * 设置TypeScript类型
     * @param typeScript TypeScript类型
     */
    public void setTypeScript(String typeScript) {
        this.typeScript = typeScript;
    }
}
