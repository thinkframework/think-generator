package io.github.thinkframework.generator.core.internal.adapter;

import io.github.thinkframework.generator.core.internal.lang.Clazz;
import io.github.thinkframework.generator.core.internal.lang.annotation.ClazzAnnotations;
import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzMethod;
import io.github.thinkframework.generator.core.internal.proxy.Remakrs;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.Column;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.ImportedKey;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.impl.ColumnImpl;

import java.util.Collection;

/**
 * 适配数据库的Column和Java字段
 *
 * @author hdhxby
 * @since 2017/5/16.
 */
public class ColumnMethodAdapter extends AbstractAdapter implements ClazzMethod, Column, Remakrs {
    private ClazzMethod clazzMehod;
    private Column column;

    public ColumnMethodAdapter(Column column,ClazzMethod clazzMehod) {
        this.column = column;
        this.clazzMehod = clazzMehod;
    }

    @Override
    public String getName() {
        return clazzMehod.getName();
    }

    @Override
    public Clazz getReturnType() {
        return clazzMehod.getReturnType();
    }

    @Override
    public Collection<Clazz> getParameterTypes() {
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

    public boolean getExportedKey() {
        return ((ColumnImpl) column).isExportedKey();
    }

    public boolean getIsImportedKey() {
        return ((ColumnImpl) column).getIsImportedKey();
    }

    public ImportedKey getImportedKey() {
        return ((ColumnImpl) column).getImportedKey();
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
