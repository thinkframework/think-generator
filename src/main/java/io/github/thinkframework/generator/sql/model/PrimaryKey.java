package io.github.thinkframework.generator.sql.model;

/**
 * @author lixiaobin
 */
public class PrimaryKey {
    private String tableName;
    private String columnName;
    private short keySeq;
    private String pkName;

    public PrimaryKey() {
    }

    public PrimaryKey(String columnName) {
        this.columnName = columnName;
    }

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
     * @return COLUMN_NAME String - 列名称
     */
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @return KEY_SEQ short - 主键中的序列号（值 1 表示主键中的第一列，值 2 表示主键中的第二列）。
     */
    public short getKeySeq() {
        return keySeq;
    }

    public void setKeySeq(short keySeq) {
        this.keySeq = keySeq;
    }

    /**
     * @return PK_NAME String - 主键的名称（可为 null）
     */
    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    @Override
    public String toString() {
        return columnName;
    }
}
