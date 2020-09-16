package io.github.thinkframework.generator.internal.sql.databasemetadata;

public interface ExportedKey {
    /**
     * @return PKTABLE_NAME String - 主键表名称
     */
    public String getPktableName();

    /**
     * @return PKCOLUMN_NAME String - 主键列名称
     */
    public String getPkcolumnName();

    /**
     * @return FKTABLE_NAME String - 被导入的外键表名称
     */
    public String getFktableName();

    /**
     * @return FKCOLUMN_NAME String - 被导入的外键列名称
     */
    public String getFkcolumnName();

    /**
     * @return KEY_SEQ short - 外键中的序列号（值 1 表示外键中的第一列，值 2 表示外键中的第二列）。
     */
    public short getKeySeq();

    /**
     * @return FK_NAME String - 外键的名称（可为 null）
     */
    public String getFkName();

    /**
     * @return PK_NAME String - 主键的名称（可为 null）
     */
    public String getPkName();
}
