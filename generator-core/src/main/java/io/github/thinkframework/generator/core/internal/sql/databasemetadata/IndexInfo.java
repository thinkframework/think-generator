package io.github.thinkframework.generator.core.internal.sql.databasemetadata;

public interface IndexInfo {

    /**
     * @return TABLE_NAME String - 表名称
     */
    String getTableName();

    /**
     * @return NON_UNIQUE boolean - 索引值是否可以不唯一。TYPE 为 tableIndexStatistic 时索引值为 false
     */
    boolean getNonUnique();
    /**
     * @return INDEX_QUALIFIER String - 索引类别（可为 null）；TYPE 为 tableIndexStatistic 时索引类别为 null
     */
    String getIndexQualifier();
    /**
     * @return INDEX_NAME String - 索引名称；TYPE 为 tableIndexStatistic 时索引名称为 null
     */
    String getIndexName();

    /**
     * @return TYPE short - 索引类型：
     * tableIndexStatistic - 此标识与表的索引描述一起返回的表统计信息
     * tableIndexClustered - 此为集群索引
     * tableIndexHashed - 此为散列索引
     * tableIndexOther - 此为某种其他样式的索引
     */
    short getType();

    /**
     * @return ORDINAL_POSITION short - 索引中的列序列号；TYPE 为 tableIndexStatistic 时该序列号为零
     */
    short getOrdinalPosition();

    /**
     * @return COLUMN_NAME String - 列名称；TYPE 为 tableIndexStatistic 时列名称为 null
     */
    String getColumnName();
}
