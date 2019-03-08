package io.github.thinkframework.generator.sql.model.impl;

import io.github.thinkframework.generator.sql.model.*;
import io.thinkframework.generator.sql.model.*;
import org.think.generator.sql.model.*;

import java.util.Collection;
import java.util.HashSet;

public class TableImpl implements Table {

    private String tableName;
    private String tableType;
    private String remarks;
    private PrimaryKey primaryKey;
    private Collection<Column> columns = new HashSet<Column>();
    private Collection<PrimaryKey> primaryKeys = new HashSet<PrimaryKey>();
    private Collection<ExportedKey> exportedKeys = new HashSet<ExportedKey>();
    private Collection<ImportedKey> importedKeys = new HashSet<ImportedKey>();
    private Collection<IndexInfo> indexInfos = new HashSet<IndexInfo>();

    /**
     * @return TABLE_NAME String - 表名称
     */
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return TABLE_TYP String - 表类型。典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。
     */
    public String getTableType() {
        return tableType;
    }

    public void setTableType(String type) {
        this.tableType = tableType;
    }

    /**
     * @return REMARKS String - 表的解释性注释
     */
    public String getRemarks() {
        return null == remarks ? "" : remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Collection<Column> getColumns() {
        return columns;
    }

    public void setColumns(Collection<Column> columns) {
        this.columns = columns;
    }

    public Collection<PrimaryKey> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(Collection<PrimaryKey> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public Collection<ExportedKey> getExportedKeys() {
        return exportedKeys;
    }

    public void setExportedKeys(Collection<ExportedKey> exportedKeys) {
        this.exportedKeys = exportedKeys;
    }

    public Collection<ImportedKey> getImportedKeys() {
        return importedKeys;
    }

    public void setImportedKeys(Collection<ImportedKey> importedKeys) {
        this.importedKeys = importedKeys;
    }

    public Collection<IndexInfo> getIndexInfos() {
        return indexInfos;
    }

    public void setIndexInfos(Collection<IndexInfo> indexInfos) {
        this.indexInfos = indexInfos;
    }

    @Override
    public String toString() {
        return getTableName();
    }

}
