package io.github.thinkframework.generator.adapter;

import io.github.thinkframework.generator.lang.Clazz;
import io.github.thinkframework.generator.lang.annotation.ClazzAnnotations;
import io.github.thinkframework.generator.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.lang.reflect.ClazzField;
import io.github.thinkframework.generator.lang.reflect.ClazzFieldRemarksDecroate;
import io.github.thinkframework.generator.lang.reflect.impl.ClazzFieldImpl;
import io.github.thinkframework.generator.sql.model.Column;
import io.github.thinkframework.generator.sql.model.ImportedKey;
import io.github.thinkframework.generator.sql.model.impl.ColumnImpl;
import io.github.thinkframework.generator.util.StringUtils;
import io.github.thinkframework.generator.util.TypesUtils;

/**
 * 适配数据库的Column和Java字段
 *
 * @author lixiaobin
 * @since 2017/5/16.
 */
public class ColumnFieldAdapter implements ClazzField, Column {
    private ClazzField clazzField;
    private Column column;
    private boolean columnField;
    private boolean ignore;
    private String typeScript;

    public ColumnFieldAdapter(Column column) {
        this.column = column;
        clazzField = buildField(column);
        columnField = true;
    }

    public ColumnFieldAdapter(ClazzField clazzField) {
        this.clazzField = clazzField;
        column = buildColumn(clazzField);
    }

    public static ClazzField buildField(Column column) {
        String columnName = column.getColumnName();
        Class clazz = TypesUtils.dataType(column.getDataType());

        ClazzImpl classType = new ClazzImpl(clazz);

        String fieldName = StringUtils.fieldName(columnName);
        ClazzFieldImpl field = new ClazzFieldImpl();
        field.setType(classType);
        field.setName(fieldName);

        return new ClazzFieldRemarksDecroate(field,StringUtils.isNotEmpty(column.getRemarks()) ? column.getRemarks() : column.getColumnName());
    }

    public static Column buildColumn(ClazzField clazzField) {
        String columnName = clazzField.getName();
//        Class clazz = TypesUtils.dataType(clazzField.getDataType());

        ColumnImpl column = new ColumnImpl(columnName);
        return column;
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
        return ((ColumnImpl) column).isExportedKey();
    }

    public boolean getIsImportedKey() {
        return column.getIsImportedKey();
    }

    public ImportedKey getImportedKey() {
        return column.getImportedKey();
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
     *
     * @return 是否忽略
     */
    public boolean isIgnore() {
        return ignore;
    }

    /**
     * 设置是否忽略当前字段
     *
     * @param ignore 是否忽略
     */
    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

    /**
     * 获取TypeScript类型.
     *
     * @return TypeScript类型
     */
    public String getTypeScript() {
        return typeScript;
    }

    /**
     * 设置TypeScript类型
     *
     * @param typeScript TypeScript类型
     */
    public void setTypeScript(String typeScript) {
        this.typeScript = typeScript;
    }
}
