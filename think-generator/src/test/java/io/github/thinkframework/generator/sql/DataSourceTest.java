package io.github.thinkframework.generator.sql;

import io.github.thinkframework.generator.sql.model.ExportedKey;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.think.generator.sql.model.*;
import io.github.thinkframework.generator.sql.model.impl.ColumnImpl;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * PDM的测试文件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DataSourceTest extends AbstractJUnit4SpringContextTests {
	Log log = LogFactory.getLog(getClass());
	private String tableName;
	@Resource(name="mysql")
	private DataSource dataSource;
	private TableFactory tableFactory;

	@Before
	public void setup(){
		tableName = "rbac_user";
		tableFactory = new TableFactory(dataSource);
	}

	@Test
	public void testGetTableTypes(){
		Collection collection = tableFactory.getTableTypes();
		assertNotNull(collection);
		assertTrue(collection.size() >0);
	}


	/**
	 * table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
	 */
	@Test
	public void testGetTables() {
		Collection<TableImpl> tables = tableFactory.getTables(tableName);
		assertNotNull(tables);
		assertTrue(tables.size()>0);
		
		
    }

	@Test
	public void testGetColumns() throws SQLException {
		DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
		ResultSet rs = dbmd.getColumns("", "", tableName, "%");
		while (rs.next()) {
			ColumnImpl column = new ColumnImpl();
			//TABLE_NAME String => 表名称
			assertNotNull(rs.getString("TABLE_NAME"));
			log.debug("TABLE_NAME\t"+rs.getString("TABLE_NAME"));
			//COLUMN_NAME String => 表名称
			assertNotNull(rs.getString("COLUMN_NAME"));
			log.debug("COLUMN_NAME\t"+rs.getString("COLUMN_NAME"));
			//DATA_TYPE int => 来自 java.sql.Types 的 SQL 类型
			assertNotNull(rs.getString("DATA_TYPE"));
			log.debug("DATA_TYPE\t"+rs.getInt("DATA_TYPE"));
			//TYPE_NAME String => 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的

			assertNotNull(rs.getString("TYPE_NAME"));
			log.debug("TYPE_NAME\t"+rs.getString("TYPE_NAME"));
			//COLUMN_SIZE int => 列的大小
			assertNotNull(rs.getString("COLUMN_SIZE"));
			log.debug("COLUMN_SIZE\t"+rs.getInt("COLUMN_SIZE"));
			// DECIMAL_DIGITS int => 小数部分的位数。对于 DECIMAL_DIGITS 不适用的数据类型，则返回 Null
			assertNotNull(rs.getString("DECIMAL_DIGITS"));
			log.debug("DECIMAL_DIGITS\t"+rs.getInt("DECIMAL_DIGITS"));
			//NUM_PREC_RADIX int => 基数（通常为 10 或 2）
			assertNotNull(rs.getString("NUM_PREC_RADIX"));
			log.debug("NUM_PREC_RADIX\t"+rs.getInt("NUM_PREC_RADIX"));
                     /*columnNoNulls - 可能不允许使用 NULL 值
                     columnNullable - 明确允许使用 NULL 值
                     columnNullableUnknown - 不知道是否可使用 null
                    NULLABLE int => 是否允许使用 NULL*/
			assertNotNull(rs.getString("NULLABLE"));
			log.debug("NULLABLE\t"+rs.getInt("NULLABLE"));
			//REMARKS String => 描述列的注释（可为 null）
			assertNotNull(rs.getString("REMARKS"));
			log.debug("REMARKS\t"+rs.getString("REMARKS"));
//                    if (column.getRemarks() == null && isOracleDataBase()) {
//                        column.setRemarks(getOracleColumnComments(column.getTableName(), column.getColumnName()));
//                    }
			//COLUMN_DEF String => 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null）
			assertNotNull(rs.getString("COLUMN_DEF"));
			log.debug("COLUMN_DEF\t"+rs.getString("COLUMN_DEF"));
			//CHAR_OCTET_LENGTH int => 对于 char 类型，该长度是列中的最大字节数
//			assertNotNull(rs.getString("CHAR_OCTET_LENGTH"));
//			logger.debug("CHAR_OCTET_LENGTH\t"+rs.getInt("CHAR_OCTET_LENGTH"));
			//ORDINAL_POSITION int => 表中的列的索引（从 1 开始）
			assertNotNull(rs.getString("ORDINAL_POSITION"));
			log.debug("ORDINAL_POSITION\t"+rs.getInt("ORDINAL_POSITION"));
                     /*YES --- 如果参数可以包括 NULL
                     NO --- 如果参数不可以包括 NULL
                     空字符串 --- 如果不知道参数是否可以包括 null*/
			//IS_NULLABLE String => ISO 规则用于确定列是否包括 null。
			assertNotNull(rs.getString("IS_NULLABLE"));
			log.debug("IS_NULLABLE\t"+rs.getString("IS_NULLABLE"));
                    /*
                 /-*IS_AUTOINCREMENT String => 指示此列是否自动增加
                 YES --- 如果该列自动增加
                 NO --- 如果该列不自动增加*/
			//IS_AUTOINCREMENT String => 指示此列是否自动增加
//			assertNotNull(rs.getString("IS_AUTOINCREMENT"));
//			logger.debug("IS_AUTOINCREMENT\t"+rs.getString("IS_AUTOINCREMENT"));
		}
	}

	@Test
	public void testGetPrimaryKeys() throws SQLException {
		DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
		ResultSet rs = dbmd.getPrimaryKeys("", "", tableName);
		while (rs.next()) {
			//String => 表名称
			assertNotNull(rs.getString("TABLE_NAME"));
			log.debug(rs.getString("TABLE_NAME"));
			//String => 列名称
			assertNotNull(rs.getString("COLUMN_NAME"));
			log.debug(rs.getString("COLUMN_NAME"));
			//short => 主键中的序列号（值 1 表示主键中的第一列，值 2 表示主键中的第二列）
			assertNotNull(rs.getString("KEY_SEQ"));
			log.debug(rs.getShort("KEY_SEQ"));
			//String => 主键的名称（可为 null）
			assertNotNull(rs.getString("PK_NAME"));
			log.debug(rs.getString("PK_NAME"));
		}
	}

	@Test
	public void testGetIndexInfo() throws SQLException {
		DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
		ResultSet rs = dbmd.getIndexInfo("", "", tableName, false, false);
		while (rs.next()) {
			//String => 表名称
			assertNotNull(rs.getString("TABLE_NAME"));
			log.debug(rs.getString("TABLE_NAME"));
			//boolean => 索引值是否可以不唯一。TYPE 为 tableIndexStatistic 时索引值为 false
			assertNotNull(rs.getString("NON_UNIQUE"));
			log.debug(rs.getBoolean("NON_UNIQUE"));
			//String => 索引类别（可为 null）；TYPE 为 tableIndexStatistic 时索引类别为 null
			assertNotNull(rs.getString("INDEX_QUALIFIER"));
			log.debug(rs.getString("INDEX_QUALIFIER"));
			//String => 索引名称；TYPE 为 tableIndexStatistic 时索引名称为 null
			assertNotNull(rs.getString("INDEX_NAME"));
			log.debug(rs.getString("INDEX_NAME"));
            	/*tableIndexStatistic - 此标识与表的索引描述一起返回的表统计信息
            	tableIndexClustered - 此为集群索引
            	tableIndexHashed - 此为散列索引
            	tableIndexOther - 此为某种其他样
            	short => 索引类型： 式的索引
            	*/
//            	indexInfo.setType(rs.getString("TYPE"));
			//short => 索引中的列序列号；TYPE 为 tableIndexStatistic 时该序列号为零
			assertNotNull(rs.getString("ORDINAL_POSITION"));
			log.debug(rs.getShort("ORDINAL_POSITION"));
			//String => 列名称；TYPE 为 tableIndexStatistic 时列名称为 null
			assertNotNull(rs.getString("COLUMN_NAME"));
			log.debug(rs.getString("COLUMN_NAME"));
			//String => 列排序序列，"A" => 升序，"D" => 降序，如果排序序列不受支持，可能为 null；TYPE 为 tableIndexStatistic 时排序序列为 null
			assertNotNull(rs.getString("ASC_OR_DESC"));
			log.debug(rs.getString("ASC_OR_DESC"));
			//int => TYPE 为 tableIndexStatistic 时，它是表中的行数；否则，它是索引中唯一值的数量。
			assertNotNull(rs.getString("CARDINALITY"));
			log.debug(rs.getInt("CARDINALITY"));
			//int => TYPE 为 tableIndexStatisic 时，它是用于表的页数，否则它是用于当前索引的页数。
			assertNotNull(rs.getString("PAGES"));
			log.debug(rs.getString("PAGES"));
			//String => 过滤器条件，如果有的话。（可能为 null）
			assertNotNull(rs.getString("FILTER_CONDITION"));
			log.debug(rs.getString("FILTER_CONDITION"));
		}
	}

	@Test
	public void testGetImportedKeys() throws SQLException {
		DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
		ResultSet rs = dbmd.getImportedKeys("", "", tableName);
		while (rs.next()) {
			//PKTABLE_NAME String => 被导入的主键表名称
			assertNotNull(rs.getString("PKTABLE_NAME"));
			log.debug(rs.getString("PKTABLE_NAME"));
			//PKCOLUMN_NAME String => 被导入的主键列名称
			assertNotNull(rs.getString("PKCOLUMN_NAME"));
			log.debug(rs.getString("PKCOLUMN_NAME"));
			//FKTABLE_NAME String => 外键表名称
			assertNotNull(rs.getString("FKTABLE_NAME"));
			log.debug(rs.getString("FKTABLE_NAME"));
			//FKCOLUMN_NAME String => 外键列名称
			assertNotNull(rs.getString("FKCOLUMN_NAME"));
			log.debug(rs.getString("FKCOLUMN_NAME"));
			//KEY_SEQ short => 外键中的序列号（值 1 表示外键中的第一列，值 2 表示外键中的第二列）。
			assertNotNull(rs.getString("KEY_SEQ"));
			log.debug(rs.getShort("KEY_SEQ"));
		}
	}

	@Test
	public void testGetExportedKeys() throws SQLException {
		DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
		ResultSet rs = dbmd.getExportedKeys("", "", tableName);
		while (rs.next()) {
			ExportedKey exportedKey = new ExportedKey();
			//String => 主键表名称
			assertNotNull(rs.getString("PKTABLE_NAME"));
			log.debug(rs.getString("PKTABLE_NAME"));
			//String => 主键列名称
			assertNotNull(rs.getString("PKCOLUMN_NAME"));
			log.debug(rs.getString("PKCOLUMN_NAME"));
			//String => 被导入的外键表类别（可能为 null），该字符串可能为 null
			//String => 被导入的外键表名称
			assertNotNull(rs.getString("FKTABLE_NAME"));
			log.debug(rs.getString("FKTABLE_NAME"));
			//String => 被导入的外键列名称
			assertNotNull(rs.getString("FKCOLUMN_NAME"));
			log.debug(rs.getString("FKCOLUMN_NAME"));
			//short => 外键中的序列号（值 1 表示外键中的第一列，值 2 表示外键中的第二列）。
			assertNotNull(rs.getString("KEY_SEQ"));
			log.debug(rs.getShort("KEY_SEQ"));
		}
	}
}
