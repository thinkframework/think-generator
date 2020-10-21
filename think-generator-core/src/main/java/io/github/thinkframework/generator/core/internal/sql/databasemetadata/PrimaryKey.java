package io.github.thinkframework.generator.core.internal.sql.databasemetadata;

public interface PrimaryKey {
    String getTableName();

    String getColumnName();

    short getKeySeq();

    String getPkName();
}
