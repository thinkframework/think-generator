package io.github.thinkframework.generator.core.internal.sql.databasemetadata.impl;

import io.github.thinkframework.generator.core.internal.sql.databasemetadata.ImportedKey;

/**
 * @author lixiaobin
 */
public class ImportedKeyImpl implements ImportedKey {
    private String pktableName;
    private String pkcolumnName;
    private String fktableName;
    private String fkcolumnName;
    private short keySeq;
    private String fkName;
    private String pkName;

    /**
     * @return PKTABLE_NAME String - 被导入的主键表名称
     */
    @Override
    public String getPktableName() {
        return pktableName;
    }

    public void setPktableName(String pktableName) {
        this.pktableName = pktableName;
    }

    /**
     * @return PKCOLUMN_NAME String - 被导入的主键列名称
     */
    @Override
    public String getPkcolumnName() {
        return pkcolumnName;
    }

    public void setPkcolumnName(String pkcolumnName) {
        this.pkcolumnName = pkcolumnName;
    }

    /**
     * @return FKTABLE_NAME String - 外键表名称
     */
    @Override
    public String getFktableName() {
        return fktableName;
    }

    public void setFktableName(String fktableName) {
        this.fktableName = fktableName;
    }

    /**
     * @return FKCOLUMN_NAME String - 外键列名称
     */
    @Override
    public String getFkcolumnName() {
        return fkcolumnName;
    }

    public void setFkcolumnName(String fkcolumnName) {
        this.fkcolumnName = fkcolumnName;
    }

    /**
     * @return KEY_SEQ short - 外键中的序列号（值 1 表示外键中的第一列，值 2 表示外键中的第二列）。
     */
    @Override
    public short getKeySeq() {
        return keySeq;
    }

    public void setKeySeq(short keySeq) {
        this.keySeq = keySeq;
    }

    /**
     * @return FK_NAME String - 外键的名称（可为 null）
     */
    @Override
    public String getFkName() {
        return fkName;
    }

    public void setFkName(String fkName) {
        this.fkName = fkName;
    }

    /**
     * @return PK_NAME String - 主键的名称（可为 null）
     */
    @Override
    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
