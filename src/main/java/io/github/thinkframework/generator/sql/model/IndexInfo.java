package io.github.thinkframework.generator.sql.model;

import java.util.Set;

/**
 * @author lixiaobin
 */
public class IndexInfo {
    private String tableName;
    private boolean nonUnique;
    private String indexQualifier;
    private String indexName;
    private short type;
    private short ordinalPosition;
    private String columnName;

    public Set<Column> columns;

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
     * @return NON_UNIQUE boolean - 索引值是否可以不唯一。TYPE 为 tableIndexStatistic 时索引值为 false
     */
    public boolean getNonUnique() {
        return nonUnique;
    }

    public void setNonUnique(boolean nonUnique) {
        this.nonUnique = nonUnique;
    }

    /**
     * @return INDEX_QUALIFIER String - 索引类别（可为 null）；TYPE 为 tableIndexStatistic 时索引类别为 null
     */
    public String getIndexQualifier() {
        return indexQualifier;
    }

    public void setIndexQualifier(String indexQualifier) {
        this.indexQualifier = indexQualifier;
    }

    /**
     * @return INDEX_NAME String - 索引名称；TYPE 为 tableIndexStatistic 时索引名称为 null
     */
    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    /**
     * @return TYPE short - 索引类型：
     * tableIndexStatistic - 此标识与表的索引描述一起返回的表统计信息
     * tableIndexClustered - 此为集群索引
     * tableIndexHashed - 此为散列索引
     * tableIndexOther - 此为某种其他样式的索引
     */
    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    /**
     * @return ORDINAL_POSITION short - 索引中的列序列号；TYPE 为 tableIndexStatistic 时该序列号为零
     */
    public short getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(short ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    /**
     * @return COLUMN_NAME String - 列名称；TYPE 为 tableIndexStatistic 时列名称为 null
     */
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String toString() {
        return columnName;
    }

}
