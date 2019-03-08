package io.github.thinkframework.generator.sql;

import io.github.thinkframework.generator.sql.model.*;
import io.thinkframework.generator.sql.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.think.generator.sql.model.*;
import io.github.thinkframework.generator.sql.model.impl.TableImpl;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TableFactoryTest extends AbstractJUnit4SpringContextTests {
	Log log = LogFactory.getLog(getClass());
	private String tableName;
	@Resource(name="mysql")
	private DataSource dataSource;
	private TableFactory tableFactory;

	@Before
	public void setup(){
		tableName = "auth_user";
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
	public void testGetColumns() {
		Collection<Column> columns= tableFactory.getColumns(tableName);
		assertNotNull(columns);
		assertTrue(columns.size()>0);
	}

	@Test
	public void testGetPrimaryKeys() {
		Collection<PrimaryKey> primaryKeys = tableFactory.getPrimaryKeys(tableName);
		assertNotNull(primaryKeys);
		assertTrue(primaryKeys.size()>0);

	}

	@Test
	public void testGetIndexInfo() {
		Collection<IndexInfo> indexInfos = tableFactory.getIndexInfo(tableName);
		assertNotNull(indexInfos);
		assertTrue(indexInfos.size()>0);

	}

	@Test
	public void testGetImportedKeys() {
		Collection<ImportedKey> importedKeys = tableFactory.getImportedKeys(tableName);
		assertNotNull(importedKeys);
		assertTrue(importedKeys.size()>0);
	}

	@Test
	public void testGetExportedKeys() {
		Collection<ExportedKey> exportedKeys= tableFactory.getExportedKeys(tableName);
		assertNotNull(exportedKeys);
		assertTrue(exportedKeys.size()>0);
	}
}
