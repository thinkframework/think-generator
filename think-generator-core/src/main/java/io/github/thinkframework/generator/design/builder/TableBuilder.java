package io.github.thinkframework.generator.design.builder;

import io.github.thinkframework.generator.config.GeneratorProperties;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.internal.sql.databasemetadata.*;
import io.github.thinkframework.generator.internal.sql.databasemetadata.impl.ColumnImpl;
import io.github.thinkframework.generator.internal.sql.databasemetadata.impl.TableImpl;
import io.github.thinkframework.generator.util.TypesUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Types;
import java.util.Collection;
import java.util.HashSet;

/**
 * 构建者模式
 *
 * @author lixiaobin
 * @since 2017/3/24
 */
@Slf4j
public class TableBuilder {

    private Table table = new TableImpl();
    private Collection<Column> columns = new HashSet<>();
    private Collection<PrimaryKey> primaryKeys = new HashSet<>();
    private Collection<ExportedKey> exportedKeys = new HashSet<>();
    private Collection<ImportedKey> importedKeys = new HashSet<>();
    private Collection<IndexInfo> indexInfos = new HashSet<>();

    private GeneratorProperties.GeneratorConfiguration generatorConfiguration;

    public void generatorConfiguration(GeneratorProperties.GeneratorConfiguration generatorConfiguration) {
        this.generatorConfiguration = generatorConfiguration;
    }

    /**
     * 添加表名称
     *
     * @param table
     * @return TableBuilder
     */
    public TableBuilder addTable(Table table) {
        this.table = table;
        return this;
    }

    /**
     * 添加列
     *
     * @param columns
     * @return TableBuilder
     */
    public TableBuilder addColumn(Collection<Column> columns) {
        this.columns = columns;
        return this;
    }

    /**
     * 添加主键
     *
     * @param primaryKeys
     * @return TableBuilder
     */
    public TableBuilder addPrimaryKey(Collection<PrimaryKey> primaryKeys) {
        this.primaryKeys = primaryKeys;
        return this;
    }

    /**
     * 添加索引
     *
     * @param indexInfos
     * @return TableBuilder
     */
    public TableBuilder addIndexInfo(Collection<IndexInfo> indexInfos) {
        this.indexInfos = indexInfos;
        return this;
    }

    /**
     * 添加外键
     *
     * @param exportedKeys
     * @return TableBuilder
     */
    public TableBuilder addExportedKey(Collection<ExportedKey> exportedKeys) {
        this.exportedKeys = exportedKeys;
        return this;
    }

    /**
     * 添加外键
     *
     * @param importedKeys
     * @return TableBuilder
     */
    public TableBuilder addImportedKey(Collection<ImportedKey> importedKeys) {
        this.importedKeys = importedKeys;
        return this;
    }

    /**
     * 生成
     *
     * @return Table
     */
    public Table build() {
        TableImpl table = new TableImpl();
        if (columns != null && columns.size() > 0) {
            table.setColumns(columns);
        }
        if (primaryKeys != null && primaryKeys.size() > 0) {
            table.setPrimaryKeys(primaryKeys);
            for (PrimaryKey primaryKey : primaryKeys) {
                for (Column column : columns) {
                    if (column.getColumnName().equalsIgnoreCase(primaryKey.getColumnName())) {
                        ((ColumnImpl) column).setPrimaryKey(true);
                    }
                }
            }
            //TODO 生成主键?最近业务没需求
//            buildPrimaryKey();
        }
        if (indexInfos != null && indexInfos.size() > 0) {
            table.setIndexInfos(indexInfos);
            for (IndexInfo indexInfo : indexInfos) {
                for (Column column : columns) {
                    if (column.getColumnName().equalsIgnoreCase(indexInfo.getColumnName())) {
                        ((ColumnImpl) column).setIndexInfo(true);
                    }
                }
            }
        }
        if (exportedKeys != null && exportedKeys.size() > 0) {
            table.setExportedKeys(exportedKeys);
            for (ExportedKey exportedKey : exportedKeys) {
                for (Column column : columns) {
                    if (column.getColumnName().equalsIgnoreCase(exportedKey.getPkcolumnName())) {
                        ((ColumnImpl) column).setExportedKey(true);
                    }
                }
            }
        }
        if (importedKeys != null && importedKeys.size() > 0) {
            table.setImportedKeys(importedKeys);
            for (ImportedKey importedKey : importedKeys) {
                for (Column column : columns) {
                    if (column.getColumnName().equalsIgnoreCase(importedKey.getFkcolumnName())) {
                        ((ColumnImpl) column).setIsImportedKey(true);
                        ((ColumnImpl) column).setImportedKey(importedKey);
                    }
                }
            }
        }
        return table;
    }

    private void buildPrimaryKey() {
        if (primaryKeys != null && primaryKeys.size() == 1) {
            PrimaryKey primaryKey = primaryKeys.iterator().next();
            log.debug("primaryKey:{}", primaryKey);
//            table.setPrimaryKey(primaryKey);
        } else if (primaryKeys != null && primaryKeys.size() > 1) {
            log.warn("发现联合主键");
//            PrimaryKey primaryKey = primaryKeys.iterator().next();
//            if("".equalsIgnoreCase(name)) {
//                name = "ID";
//            }
//            PrimaryKey primaryKey = new PrimaryKey(name);
//            primaryKey.setColumnName(name);
//            ColumnImpl column = new ColumnImpl(name);
//            column.setPrimaryKey(true);
//            table.setPrimaryKey(primaryKey);
        } else {
            log.warn("未发现主键");
        }
    }
}
