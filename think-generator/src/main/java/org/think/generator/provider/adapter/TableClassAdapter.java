package org.think.generator.provider.adapter;

import org.think.generator.lang.Clazz;
import org.think.generator.lang.ClazzPackage;
import org.think.generator.lang.annotation.ClazzAnnotations;
import org.think.generator.lang.impl.ClazzImpl;
import org.think.generator.lang.reflect.ClazzField;
import org.think.generator.lang.reflect.ClazzMethod;
import org.think.generator.sql.model.*;

import java.util.Collection;

/**
 * 适配数据库表盒Java类
 * @author lixiaobin
 * @since 2017/5/12.
 */
public class TableClassAdapter implements Clazz,Table {
    private Clazz clazz;
    private Table table;
    public TableClassAdapter(Table table){
        this.table = table;
        this.clazz = TableClassBuild.buildClass(table);
    }

    public TableClassAdapter(Clazz clazz){

    }

    @Override
    public ClazzPackage getPackage() {
        return clazz.getPackage();
    }

    @Override
    public String getName() {
        return clazz.getName();
    }

    @Override
    public String getSimpleName() {
        return clazz.getSimpleName();
    }

    @Override
    public Collection<ClazzField> getFields() {
        return clazz.getFields();
    }

    @Override
    public Collection<ClazzMethod> getMethods() {
        return clazz.getMethods();
    }

    @Override
    public ClazzAnnotations getAnnotations() {
        return clazz.getAnnotations();
    }


    @Override
    public String getTableName() {
        return table.getTableName();
    }

    @Override
    public String getTableType() {
        return table.getTableType();
    }

    @Override
    public String getRemarks() {
        return table.getRemarks();
    }

    @Override
    public PrimaryKey getPrimaryKey() {
        return table.getPrimaryKey();
    }

    @Override
    public Collection<Column> getColumns() {
        return table.getColumns();
    }

    @Override
    public Collection<ExportedKey> getExportedKeys() {
        return table.getExportedKeys();
    }

    @Override
    public Collection<ImportedKey> getImportedKeys() {
        return table.getImportedKeys();
    }

    public Collection<ClazzField> getImportedFields() {
        return ((ClazzImpl)clazz).getImportedFields();
    }

    public Collection<ClazzField> getExportedFields() {
        return ((ClazzImpl)clazz).getExportedFields();
    }

    public Collection<ClazzMethod> getImportedMethods() {
        return ((ClazzImpl)clazz).getImportedMethods();
    }

    public Collection<ClazzMethod> getExportedMethods() {
        return ((ClazzImpl)clazz).getExportedMethods();
    }


    public Collection<IndexInfo> getIndexInfos() {
        return table.getIndexInfos();
    }
}
