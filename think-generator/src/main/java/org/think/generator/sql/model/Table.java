package org.think.generator.sql.model;

import java.util.Collection;

/**
 * @author lixiaobin
 * @since 2017/5/16.
 */
public interface Table {
    /**
     * @return TABLE_NAME String - 表名称
     */
    public String getTableName();


    /**
     * @return TABLE_TYP String - 表类型。典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。
     */
    public String getTableType();

    /**
     * @return REMARKS String - 表的解释性注释
     */
    public String getRemarks();

    public PrimaryKey getPrimaryKey();

    /**
     * 列.
     * @return 列.
     */
    public Collection<Column> getColumns();

    /**
     * 引用表.
     * @return 引用表.
     */
    public Collection<ExportedKey> getExportedKeys();

    /**
     * 外键.
     * @return 外键
     */
    public Collection<ImportedKey> getImportedKeys();

    /**
     * 索引.
     * @return 索引
     */
    public Collection<IndexInfo> getIndexInfos();
}
