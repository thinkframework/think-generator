package io.github.thinkframework.generator.sql;

import io.github.thinkframework.generator.sql.model.*;
import io.github.thinkframework.generator.sql.model.impl.ColumnImpl;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashSet;

/**
 * 构建者模式
 * @author lixiaobin
 * @since 2017/3/24
 */
public class TableBuilder {
    private TableFactory tableFactory = new TableFactory();
    private TableImpl table = new TableImpl();
    private String tableName = "";
    private Collection<Column> columns = new HashSet<Column>();
    private Collection<PrimaryKey> primaryKeys = new HashSet<PrimaryKey>();
    private Collection<ExportedKey> exportedKeys = new HashSet<ExportedKey>();
    private Collection<ImportedKey> importedKeys = new HashSet<ImportedKey>();
    private Collection<IndexInfo> indexInfos = new HashSet<IndexInfo>();

    /**
     * 添加数据源
     * @param dataSource
     * @return TableBuilder
     */
    public TableBuilder addDataSource(DataSource dataSource){
        tableFactory.setDataSource(dataSource);
        return this;
    }

    /**
     * 添加表名称
     * @param tableName
     * @return TableBuilder
     */
    public TableBuilder addTableName(String tableName){
        this.tableName = tableName;
        return this;
    }

    /**
     * 添加列
     * @param tableName
     * @return TableBuilder
     */
    public TableBuilder addColumn(String tableName){
        columns = tableFactory.getColumns(tableName);
        return this;
    }

    /**
     * 添加主键
     * @param tableName
     * @return TableBuilder
     */
    public TableBuilder addPrimaryKey(String tableName){
        primaryKeys = tableFactory.getPrimaryKeys(tableName);
        return this;
    }

    /**
     * 添加索引
     * @param tableName
     * @return TableBuilder
     */
    public TableBuilder addIndexInfo(String tableName){
        indexInfos = tableFactory.getIndexInfo(tableName);
        return this;
    }

    /**
     * 添加外键
     * @param tableName
     * @return TableBuilder
     */
    public TableBuilder addExportedKey(String tableName){
        exportedKeys = tableFactory.getExportedKeys(tableName);
        return this;
    }

    /**
     * 添加外键
     * @param tableName
     * @return TableBuilder
     */
    public TableBuilder addImportedKey(String tableName){
        importedKeys = tableFactory.getImportedKeys(tableName);
        return this;
    }

    /**
     * 生成
     * @return Table
     */
    public Table build(){
        table = tableFactory.getTable(tableName);
        if(columns != null && columns.size() > 0){
            table.setColumns(columns);
        }
        if(primaryKeys != null && primaryKeys.size()>0){
            table.setPrimaryKeys(primaryKeys);
            for(PrimaryKey primaryKey : primaryKeys){
                for(Column column : columns){
                    if(column.getColumnName().equalsIgnoreCase(primaryKey.getColumnName())){
                        ((ColumnImpl)column).setPrimaryKey(true);
                    }
                }
            }
            buildPrimaryKey();
        }
        if(indexInfos != null && indexInfos.size()>0) {
            table.setIndexInfos(indexInfos);
            for(IndexInfo indexInfo : indexInfos){
                for(Column column : columns){
                    if(column.getColumnName().equalsIgnoreCase(indexInfo.getColumnName())){
                        ((ColumnImpl)column).setIndexInfo(true);
                    }
                }
            }
        }
        if(exportedKeys != null && exportedKeys.size()>0) {
            table.setExportedKeys(exportedKeys);
            for(ExportedKey exportedKey : exportedKeys){
                for(Column column : columns){
                    if(column.getColumnName().equalsIgnoreCase(exportedKey.getPkcolumnName())){
                        ((ColumnImpl)column).setExportedKey(true);
                    }
                }
            }
        }
        if(importedKeys != null && importedKeys.size()>0) {
            table.setImportedKeys(importedKeys);
            for(ImportedKey importedKey : importedKeys){
                for(Column column : columns){
                    if(column.getColumnName().equalsIgnoreCase(importedKey.getFkcolumnName())){
                        ((ColumnImpl)column).setIsImportedKey(true);
                        ((ColumnImpl)column).setImportedKey(importedKey);
                    }
                }
            }
        }
        return table;
    }

    private void buildPrimaryKey(){
        if(primaryKeys != null && primaryKeys.size() == 1){
            PrimaryKey primaryKey = primaryKeys.iterator().next();
            table.setPrimaryKey(primaryKey);
        }
        else if(primaryKeys != null && primaryKeys.size() > 1){
//            PrimaryKey primaryKey = primaryKeys.iterator().next();
//            if("".equalsIgnoreCase(name)) {
//                name = "ID";
//            }
//            PrimaryKey primaryKey = new PrimaryKey(name);
//            primaryKey.setColumnName(name);
//            ColumnImpl column = new ColumnImpl(name);
//            column.setPrimaryKey(true);
//            table.setPrimaryKey(primaryKey);
        }else{

        }
    }
}
