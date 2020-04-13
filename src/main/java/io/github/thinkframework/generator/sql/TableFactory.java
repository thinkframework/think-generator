package io.github.thinkframework.generator.sql;

import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.sql.model.*;
import io.github.thinkframework.generator.sql.model.impl.ColumnImpl;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 根据数据库元数据(metadata)创建Table对象
 *
 * @author lixiaobin
 * @since 2017/3/24.
 */
@Slf4j
public class TableFactory {
    private final Logger logger = LoggerFactory.getLogger(TableFactory.class);

    private DataSource dataSource;

    public TableFactory() {
    }

    public TableFactory(DataSource dataSource) {
        setDataSource(dataSource);
    }

    /**
     * 获取目录
     * 获取目录
     * 获取目录
     *
     * @return 目录
     */
    protected String getCatalog() {
        String catalog = null;
        try (Connection connection = getDataSource().getConnection()) {
            catalog = connection.getCatalog();
        } catch (SQLException e) {
            logger.error("catalog: {}", e.getClass().getName());
        } finally {
            return catalog;
        }
    }

    /**
     * 获取模式
     *
     * @return 模式
     */
    protected String getSchema() {
        String schema = null;
        try (Connection connection = getDataSource().getConnection()) {
            schema = connection.getSchema();
        } catch (SQLException e) {
            logger.error("schema: {}", e.getClass().getName());
        } finally {
            return schema;
        }
    }

    protected DataSource getDataSource() {
        return dataSource;
    }


    public TableFactory setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }


    /**
     * 获得该用户下面的表,支持模糊查询
     *
     * @return 支持的表类型
     */
    public Collection<String> getTableTypes() throws GeneratorRuntimeException {
        try (Connection connection = getDataSource().getConnection()) {
            DatabaseMetaData dbmd = connection.getMetaData();
            Set<String> set = new LinkedHashSet<>();
            ResultSet rs = dbmd.getTableTypes();
            while (rs.next()) {
                set.add(rs.getString("TABLE_TYPE"));
            }
            return set;
        } catch (SQLException e) {
            throw new GeneratorRuntimeException("SQL异常", e);
        }
    }

    public TableImpl getTable(String tableName) throws GeneratorRuntimeException {
        Collection<TableImpl> tables = getTables(tableName, getTableTypes().toArray(new String[]{}));
        if (tables != null && tables.iterator().hasNext()) {
            return tables.iterator().next();
        } else {
            throw new GeneratorRuntimeException("表不存在");
        }
    }

    /**
     * 获得该用户下面的表,支持模糊查询
     *
     * @param tableName 表名称
     * @return 表的集合
     */
    public Set<TableImpl> getTables(String tableName) throws GeneratorRuntimeException {
        log.info("表名称:{}", tableName);
        Set<TableImpl> tables = getTables(tableName, getTableTypes().toArray(new String[]{}));
        return tables;
    }

    protected Set<TableImpl> getTables(final String tableName, final String[] types) throws GeneratorRuntimeException {
        try (Connection connection = getDataSource().getConnection()) {
            DatabaseMetaData dbmd = connection.getMetaData();
            Set<TableImpl> tables = new LinkedHashSet<>();
            TableImpl table;
            String tablename = tableName;
            String[] type = types;
            if (type == null || type.length == 0) {
                type = new String[]{"TABLE"};
            }

            ResultSet rs = dbmd.getTables(getCatalog(), getSchema(), tablename, type);
            while (rs.next()) {
                table = new TableImpl();
                //TABLE_NAME String => 表名称
                table.setTableName(rs.getString("TABLE_NAME"));
                //TABLE_TYP String => 表类型。典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。
                table.setTableType(rs.getString("TABLE_TYPE"));
                //REMARKS String => 表的解释性注释
                table.setRemarks(rs.getString("REMARKS"));
//                    if (StringUtils.isEmpty(table.getRemarks()) && isOracleDataBase()) {
//                        table.setRemarks(getOracleTableComments(table.getName()));
//                    }
                tables.add(table);
            }
            return tables;
        } catch (SQLException e) {
            throw new GeneratorRuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获得表或视图中的所有列信息
     *
     * @param tableName 表名称
     * @return 列的集合
     */
    public Collection<Column> getColumns(final String tableName) throws GeneratorRuntimeException {
        try (Connection connection = getDataSource().getConnection()) {
            DatabaseMetaData dbmd = connection.getMetaData();
            Set<Column> columns = new LinkedHashSet<Column>();
            ResultSet rs = dbmd.getColumns(getCatalog(), getSchema(), tableName, "%");
            while (rs.next()) {
                ColumnImpl column = new ColumnImpl();
                //TABLE_NAME String => 表名称
                column.setTableName(rs.getString("TABLE_NAME"));
                //COLUMN_NAME String => 表名称
                column.setColumnName(rs.getString("COLUMN_NAME"));
                String columName = rs.getString("COLUMN_NAME");
                //DATA_TYPE int => 来自 java.sql.Types 的 SQL 类型
                column.setDataType(rs.getInt("DATA_TYPE"));
                //TYPE_NAME String => 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的
                column.setTypeName(rs.getString("TYPE_NAME"));
                //COLUMN_SIZE int => 列的大小
                column.setColumnSize(rs.getInt("COLUMN_SIZE"));
                // DECIMAL_DIGITS int => 小数部分的位数。对于 DECIMAL_DIGITS 不适用的数据类型，则返回 Null
                column.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
                //NUM_PREC_RADIX int => 基数（通常为 10 或 2）
                column.setNumPrecRadix(rs.getInt("NUM_PREC_RADIX"));
                 /*columnNoNulls - 可能不允许使用 NULL 值
                 columnNullable - 明确允许使用 NULL 值
                 columnNullableUnknown - 不知道是否可使用 null
                NULLABLE int => 是否允许使用 NULL*/
                column.setNullable(rs.getInt("NULLABLE"));
                //REMARKS String => 描述列的注释（可为 null）
                column.setRemarks(rs.getString("REMARKS"));
//                    if (column.getRemarks() == null && isOracleDataBase()) {
//                        column.setRemarks(getOracleColumnComments(column.getTableName(), column.getColumnName()));
//                    }
                //COLUMN_DEF String => 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null）
                column.setColumnDef(rs.getString("COLUMN_DEF"));
                //CHAR_OCTET_LENGTH int => 对于 char 类型，该长度是列中的最大字节数
                column.setCharOctetLength(rs.getInt("CHAR_OCTET_LENGTH"));
                //ORDINAL_POSITION int => 表中的列的索引（从 1 开始）
                column.setOrdinalPosition(rs.getInt("ORDINAL_POSITION"));
                     /*YES --- 如果参数可以包括 NULL
                     NO --- 如果参数不可以包括 NULL
                     空字符串 --- 如果不知道参数是否可以包括 null*/
                //IS_NULLABLE String => ISO 规则用于确定列是否包括 null。
                column.setIsNullable(rs.getString("IS_NULLABLE"));
                /*
                 /-*IS_AUTOINCREMENT String => 指示此列是否自动增加
                 YES --- 如果该列自动增加
                 NO --- 如果该列不自动增加*/
                //IS_AUTOINCREMENT String => 指示此列是否自动增加
                column.setIsAutoincrement(rs.getString("IS_AUTOINCREMENT"));

                if (!overideColumn(column)) {
                    continue;
                }
                columns.add(column);
            }
            return columns;
        } catch (SQLException e) {
            throw new GeneratorRuntimeException("SQL异常", e);
        }
    }

    /**
     * 允许覆盖一些字段的设置
     *
     * @param column 列字段
     * @return 返回true则添加, 返回false则忽略
     */
    protected boolean overideColumn(Column column) {
        return true;
    }

    /**
     * 获得一个表的主键信息
     *
     * @param tableName 表名称
     * @return 列的集合
     */
    public Collection<PrimaryKey> getPrimaryKeys(final String tableName) throws GeneratorRuntimeException {
        try (Connection connection = getDataSource().getConnection()) {
            DatabaseMetaData dbmd = connection.getMetaData();
            Set<PrimaryKey> primaryKeys = new LinkedHashSet<PrimaryKey>();
            ResultSet rs = dbmd.getPrimaryKeys(getCatalog(), getSchema(), tableName);
            while (rs.next()) {
                PrimaryKey primaryKey = new PrimaryKey();
                //String => 表名称
                primaryKey.setTableName(rs.getString("TABLE_NAME"));
                //String => 列名称
                primaryKey.setColumnName(rs.getString("COLUMN_NAME"));
                //String => 主键的名称（可为 null）
                primaryKey.setPkName(rs.getString("PK_NAME"));
                //short => 主键中的序列号（值 1 表示主键中的第一列，值 2 表示主键中的第二列）
                primaryKey.setKeySeq(rs.getShort("KEY_SEQ"));
                primaryKeys.add(primaryKey);
            }
            return primaryKeys;
        } catch (SQLException e) {
            throw new GeneratorRuntimeException("SQL异常", e);
        }
    }


    /**
     * 获得一个表的索引信息
     *
     * @param tableName 表名称
     * @return 索引的集合
     */
    public Collection<IndexInfo> getIndexInfo(final String tableName) throws GeneratorRuntimeException {
        try (Connection connection = getDataSource().getConnection()) {
            Set<IndexInfo> indexInfos = new HashSet<IndexInfo>();
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet rs = dbmd.getIndexInfo(getCatalog(), getSchema(), tableName, false, false);
            while (rs.next()) {
                IndexInfo indexInfo = new IndexInfo();
                //String => 表名称
                indexInfo.setTableName(rs.getString("TABLE_NAME"));
                //String => 列名称；TYPE 为 tableIndexStatistic 时列名称为 null
                indexInfo.setColumnName(rs.getString("COLUMN_NAME"));
                //boolean => 索引值是否可以不唯一。TYPE 为 tableIndexStatistic 时索引值为 false
                indexInfo.setNonUnique(rs.getBoolean("NON_UNIQUE"));
                //String => 索引类别（可为 null）；TYPE 为 tableIndexStatistic 时索引类别为 null
                indexInfo.setIndexQualifier(rs.getString("INDEX_QUALIFIER"));
                //String => 索引名称；TYPE 为 tableIndexStatistic 时索引名称为 null
                indexInfo.setIndexName(rs.getString("INDEX_NAME"));
            	/*tableIndexStatistic - 此标识与表的索引描述一起返回的表统计信息
            	tableIndexClustered - 此为集群索引
            	tableIndexHashed - 此为散列索引
            	tableIndexOther - 此为某种其他样
            	short => 索引类型： 式的索引
            	*/
                indexInfo.setType(rs.getShort("TYPE"));
                //short => 索引中的列序列号；TYPE 为 tableIndexStatistic 时该序列号为零
                indexInfo.setOrdinalPosition(rs.getShort("ORDINAL_POSITION"));
                indexInfos.add(indexInfo);
            }
            return indexInfos;
        } catch (SQLException e) {
            throw new GeneratorRuntimeException("SQL异常", e);
        }
    }


    /**
     * 获得一个表的外键信息
     *
     * @param tableName 表名称
     * @return 主键的集合
     */
    public Collection<ImportedKey> getImportedKeys(final String tableName) throws GeneratorRuntimeException {
        try (Connection connection = getDataSource().getConnection()) {
            DatabaseMetaData dbmd = connection.getMetaData();
            Set<ImportedKey> importedKeys = new HashSet<ImportedKey>();
            ResultSet rs = dbmd.getImportedKeys(getCatalog(), getSchema(), tableName);
            while (rs.next()) {
                ImportedKey importedKey = new ImportedKey();
                //PKTABLE_NAME String => 被导入的主键表名称
                importedKey.setPktableName(rs.getString("PKTABLE_NAME"));
                //PKCOLUMN_NAME String => 被导入的主键列名称
                importedKey.setPkcolumnName(rs.getString("PKCOLUMN_NAME"));
                //FKTABLE_NAME String => 外键表名称
                importedKey.setFktableName(rs.getString("FKTABLE_NAME"));
                //FKCOLUMN_NAME String => 外键列名称
                importedKey.setFkcolumnName(rs.getString("FKCOLUMN_NAME"));
                //KEY_SEQ short => 外键中的序列号（值 1 表示外键中的第一列，值 2 表示外键中的第二列）。
                importedKey.setKeySeq(rs.getShort("KEY_SEQ"));


                importedKey.setFkName(rs.getString("FK_NAME"));
                importedKey.setPkName(rs.getString("PK_NAME"));

                importedKeys.add(importedKey);
            }
            return importedKeys;
        } catch (SQLException e) {
            throw new GeneratorRuntimeException("SQL异常", e);
        }
    }


    /**
     * 获得一个表的外键信息
     *
     * @param tableName 表名称
     * @return 外键的集合
     */
    public Collection<ExportedKey> getExportedKeys(final String tableName) throws GeneratorRuntimeException {
        try (Connection connection = getDataSource().getConnection()) {
            DatabaseMetaData dbmd = connection.getMetaData();
            Set<ExportedKey> exportedKeys = new LinkedHashSet<>();
            ResultSet rs = dbmd.getExportedKeys(getCatalog(), getSchema(), tableName);
            while (rs.next()) {
                ExportedKey exportedKey = new ExportedKey();
                //String => 主键表名称
                exportedKey.setPktableName(rs.getString("PKTABLE_NAME"));
                //String => 主键列名称
                exportedKey.setPkcolumnName(rs.getString("PKCOLUMN_NAME"));
                //String => 被导入的外键表名称
                exportedKey.setFktableName(rs.getString("FKTABLE_NAME"));
                //String => 被导入的外键列名称
                exportedKey.setFkcolumnName(rs.getString("FKCOLUMN_NAME"));
                //short => 外键中的序列号（值 1 表示外键中的第一列，值 2 表示外键中的第二列）。
                exportedKey.setKeySeq(rs.getShort("KEY_SEQ"));

                exportedKey.setFkName(rs.getString("FK_NAME"));
                exportedKey.setPkName(rs.getString("PK_NAME"));

                exportedKeys.add(exportedKey);
            }
            return exportedKeys;
        } catch (SQLException e) {
            throw new GeneratorRuntimeException("SQL异常", e);
        }
    }
}
