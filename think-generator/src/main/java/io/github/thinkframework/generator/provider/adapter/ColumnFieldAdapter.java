package io.github.thinkframework.generator.provider.adapter;

import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.lang.Clazz;
import io.github.thinkframework.generator.lang.annotation.ClazzAnnotations;
import io.github.thinkframework.generator.lang.reflect.ClazzField;
import io.github.thinkframework.generator.sql.model.ImportedKey;
import io.github.thinkframework.generator.sql.model.impl.ColumnImpl;
import io.github.thinkframework.generator.sql.model.Column;
import io.github.thinkframework.generator.util.TypesUtils;

import java.util.Arrays;

/**
 * 适配数据库的Column和Java字段
 * @author lixiaobin
 * @since 2017/5/16.
 */
public class ColumnFieldAdapter implements ClazzField,Column {
    private ClazzField clazzField;
    private Column column;
    private boolean columnField;
    private boolean ignore;
    private String typeScript;
    public ColumnFieldAdapter(Column column){
        this.column = column;
        clazzField = ColumnFieldBuild.buildField(column);
        typeScript = TypesUtils.ConvertTypeScript(column.getDataType());
        columnField = true;
    }

    public ColumnFieldAdapter(ClazzField clazzField){
        this.clazzField = clazzField;
        column = new ColumnImpl();
    }

    public ColumnFieldAdapter(ClazzField clazzField,String remark){
        this.clazzField = clazzField;
        column = new ColumnImpl();
        ((ColumnImpl)column).setRemarks(remark);
    }

    @Override
    public String getName() {
        return clazzField.getName();
    }

    @Override
    public Clazz getType() {
        return clazzField.getType();
    }

    @Override
    public ClazzAnnotations getAnnotations() {
        return clazzField.getAnnotations();
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
        return Arrays.asList(((String)GeneratorContext.get().getProperty("ignore")).split(","))
                .contains(getColumnName());
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
