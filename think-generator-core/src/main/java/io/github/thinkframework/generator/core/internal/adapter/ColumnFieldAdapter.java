package io.github.thinkframework.generator.core.internal.adapter;

import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.lang.annotation.ClazzAnnotations;
import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzField;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Column;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.ImportedKey;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.impl.ColumnImpl;

/**
 * 适配数据库的Column和Java字段
 *
 * @author hdhxby
 * @since 2017/5/16.
 */
public class ColumnFieldAdapter extends AbstractAdapter implements ClazzField, Column, Remakrs {
    private ClazzField clazzField;
    private Column column;

    public ColumnFieldAdapter(Column column,ClazzField clazzField) {
        this.column = column;
        this.clazzField = clazzField;
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

    public boolean getExportedKey() {
        return ((ColumnImpl) column).isExportedKey();
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

}
