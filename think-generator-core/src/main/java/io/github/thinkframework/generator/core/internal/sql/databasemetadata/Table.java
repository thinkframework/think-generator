package io.github.thinkframework.generator.core.internal.sql.databasemetadata;

import java.util.Collection;

/**
 * @author lixiaobin
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
}
