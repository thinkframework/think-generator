package io.github.thinkframework.generator.core.internal.sql.databasemetadata;

/**
 * @see java.sql.DatabaseMetaData
 */
public interface Column {

    /**
     * @return COLUMN_NAME String - 列名称
     */
    String getColumnName();

    /**
     * @return REMARKS String - 描述列的注释（可为 null）
     */
    String getRemarks();

    /**
     * @return DATA_TYPE int - 来自 java.sql.Types 的 SQL 类型
     */
    int getDataType();

    boolean getPrimaryKey();

    boolean getIsImportedKey();

    ImportedKey getImportedKey();

    /**
     * @return IS_NULLABLE String - ISO 规则用于确定列是否包括 null。
     * YES --- 如果参数可以包括 NULL
     * NO --- 如果参数不可以包括 NULL
     * 空字符串 --- 如果不知道参数是否可以包括 null
     */
    String getIsNullable();

    /**
     * @return COLUMN_SIZE int - 列的大小
     */
    int getColumnSize();
}
