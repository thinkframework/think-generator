package io.github.thinkframework.generator.lang;

import io.github.thinkframework.generator.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.sql.model.*;
import io.github.thinkframework.generator.sql.model.impl.ColumnImpl;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashSet;

/**
 * 构建者模式
 *
 * @author lixiaobin
 * @since 2017/3/24
 */
@Slf4j
public class ClazzBuilder {

    private String tableName = "";
    private ClazzImpl clazz = new ClazzImpl();
    private Collection<Column> columns = new HashSet<Column>();
    private Collection<PrimaryKey> primaryKeys = new HashSet<PrimaryKey>();
    private Collection<ExportedKey> exportedKeys = new HashSet<ExportedKey>();
    private Collection<ImportedKey> importedKeys = new HashSet<ImportedKey>();
    private Collection<IndexInfo> indexInfos = new HashSet<IndexInfo>();

    /**
     * 添加表名称
     *
     * @param clazz
     * @return ClazzBuilder
     */
    public ClazzBuilder addClass(ClazzImpl clazz) {
        this.clazz = clazz;
        return this;
    }

    /**
     * 添加列
     *
     * @param columns
     * @return TableBuilder
     */
    public ClazzBuilder addField(Collection<Column> columns) {
        this.columns = columns;
        return this;
    }

//    /**
//     * 添加主键
//     *
//     * @param primaryKeys
//     * @return TableBuilder
//     */
//    public ClazzBuilder addPrimaryKey(Collection<PrimaryKey> primaryKeys) {
//        this.primaryKeys = primaryKeys;
//        return this;
//    }
//
//    /**
//     * 添加索引
//     *
//     * @param indexInfos
//     * @return TableBuilder
//     */
//    public ClazzBuilder addIndexInfo(Collection<IndexInfo> indexInfos) {
//        this.indexInfos = indexInfos;
//        return this;
//    }
//
//    /**
//     * 添加外键
//     *
//     * @param exportedKeys
//     * @return TableBuilder
//     */
//    public ClazzBuilder addExportedKey(Collection<ExportedKey> exportedKeys) {
//        this.exportedKeys = exportedKeys;
//        return this;
//    }
//
//    /**
//     * 添加外键
//     *
//     * @param importedKeys
//     * @return TableBuilder
//     */
//    public ClazzBuilder addImportedKey(Collection<ImportedKey> importedKeys) {
//        this.importedKeys = importedKeys;
//        return this;
//    }

    /**
     * 生成
     *
     * @return Table
     */
    public Clazz build() {
//        if (columns != null && columns.size() > 0) {
//            table.setColumns(columns);
//        }
//        if (primaryKeys != null && primaryKeys.size() > 0) {
//            table.setPrimaryKeys(primaryKeys);
//            for (PrimaryKey primaryKey : primaryKeys) {
//                for (Column column : columns) {
//                    if (column.getColumnName().equalsIgnoreCase(primaryKey.getColumnName())) {
//                        ((ColumnImpl) column).setPrimaryKey(true);
//                    }
//                }
//            }
//            buildPrimaryKey();
//        }
//        if (indexInfos != null && indexInfos.size() > 0) {
//            table.setIndexInfos(indexInfos);
//            for (IndexInfo indexInfo : indexInfos) {
//                for (Column column : columns) {
//                    if (column.getColumnName().equalsIgnoreCase(indexInfo.getColumnName())) {
//                        ((ColumnImpl) column).setIndexInfo(true);
//                    }
//                }
//            }
//        }
//        if (exportedKeys != null && exportedKeys.size() > 0) {
//            table.setExportedKeys(exportedKeys);
//            for (ExportedKey exportedKey : exportedKeys) {
//                for (Column column : columns) {
//                    if (column.getColumnName().equalsIgnoreCase(exportedKey.getPkcolumnName())) {
//                        ((ColumnImpl) column).setExportedKey(true);
//                    }
//                }
//            }
//        }
//        if (importedKeys != null && importedKeys.size() > 0) {
//            table.setImportedKeys(importedKeys);
//            for (ImportedKey importedKey : importedKeys) {
//                for (Column column : columns) {
//                    if (column.getColumnName().equalsIgnoreCase(importedKey.getFkcolumnName())) {
//                        ((ColumnImpl) column).setIsImportedKey(true);
//                        ((ColumnImpl) column).setImportedKey(importedKey);
//                    }
//                }
//            }
//        }
        return clazz;
    }
}
