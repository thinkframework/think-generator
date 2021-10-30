package io.github.thinkframework.generator.core.internal.sql.databasemetadata;

import io.github.thinkframework.generator.core.internal.sql.databasemetadata.impl.ColumnImpl;
import io.github.thinkframework.generator.core.internal.sql.databasemetadata.impl.TableImpl;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author hdhxby
 * @since 2017/5/16.
 */
public interface Table {
    /**
     * @return TABLE_NAME String - 表名称
     */
    String getTableName();


    /**
     * @return TABLE_TYP String - 表类型。典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。
     */
    String getTableType();

    /**
     * @return REMARKS String - 表的解释性注释
     */
    String getRemarks();

    PrimaryKey getPrimaryKey();

    /**
     * 列.
     *
     * @return 列.
     */
    Collection<Column> getColumns();

    /**
     * 引用表.
     *
     * @return 引用表.
     */
    Collection<ExportedKey> getExportedKeys();

    /**
     * 外键.
     *
     * @return 外键
     */
    Collection<ImportedKey> getImportedKeys();

    /**
     * 索引.
     *
     * @return 索引
     */
    Collection<IndexInfo> getIndexInfos();

    /**
     * 构建者
     * @param <T>
     */
    class Builder<T extends Table> {

        private T table;
        private Collection<Column> columns = new HashSet<>();
        private Collection<PrimaryKey> primaryKeys = new HashSet<>();
        private Collection<ExportedKey> exportedKeys = new HashSet<>();
        private Collection<ImportedKey> importedKeys = new HashSet<>();
        private Collection<IndexInfo> indexInfos = new HashSet<>();

        /**
         * 添加表名称
         *
         * @param table
         * @return TableBuilder
         */
        public Builder addTable(T table) {
            this.table = table;
            return this;
        }

        /**
         * 添加列
         *
         * @param columns
         * @return TableBuilder
         */
        public Builder addColumn(Collection<Column> columns) {
            this.columns = columns;
            return this;
        }

        /**
         * 添加主键
         *
         * @param primaryKeys
         * @return TableBuilder
         */
        public Builder addPrimaryKey(Collection<PrimaryKey> primaryKeys) {
            this.primaryKeys = primaryKeys;
            return this;
        }

        /**
         * 添加索引
         *
         * @param indexInfos
         * @return TableBuilder
         */
        public Builder addIndexInfo(Collection<IndexInfo> indexInfos) {
            this.indexInfos = indexInfos;
            return this;
        }

        /**
         * 添加外键
         *
         * @param exportedKeys
         * @return TableBuilder
         */
        public Builder addExportedKey(Collection<ExportedKey> exportedKeys) {
            this.exportedKeys = exportedKeys;
            return this;
        }

        /**
         * 添加外键
         *
         * @param importedKeys
         * @return TableBuilder
         */
        public Builder addImportedKey(Collection<ImportedKey> importedKeys) {
            this.importedKeys = importedKeys;
            return this;
        }

        /**
         * 生成
         *
         * @return Table
         */
        public Table build() {
            // TODO 到时候看看要不要降低复杂度
            TableImpl table = (TableImpl) this.table;
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
    }
}
