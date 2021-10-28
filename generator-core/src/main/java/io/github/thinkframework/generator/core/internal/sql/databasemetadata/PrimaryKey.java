package io.github.thinkframework.generator.core.internal.sql.databasemetadata;

/**
 * @see java.sql.DatabaseMetaData
 */
public interface PrimaryKey {

    /**
     * @return TABLE_NAME String - 表名称
     */
    String getTableName();

    /**
     * @return COLUMN_NAME String - 列名称
     */
    String getColumnName();


    /**
     * @return KEY_SEQ short - 外键中的序列号（值 1 表示外键中的第一列，值 2 表示外键中的第二列）。
     */
    short getKeySeq();

    /**
     * @return PK_NAME String - 主键的名称（可为 null）
     */
    String getPkName();
}
