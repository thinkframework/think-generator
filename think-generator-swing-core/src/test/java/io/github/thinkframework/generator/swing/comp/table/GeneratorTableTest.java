package io.github.thinkframework.generator.swing.comp.table;

import io.github.thinkframework.SwingApplication;
import io.github.thinkframework.boot.config.GeneratorContextLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 内部使用的,基于MetaTableModel的JTable
 *
 * @author lixiaobin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SwingApplication.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorTableTest {

    @Autowired
    private GeneratorTableFactoryBean generatorTreeFactory;

    @Autowired
    private DataSource dataSource;

    private String tableName;

    private GeneratorTable generatorTable;

    @Before
    public void before() throws SQLException {
        tableName = "TEST_TABLE";
        generatorTreeFactory.setDataSource(dataSource);
        generatorTreeFactory.setTableName(tableName);
        generatorTable = generatorTreeFactory.getObject();
    }

    @Test
    public void getModel() {
        Assert.assertTrue(generatorTable.getModel() instanceof GeneratorTableModel);
    }
}
