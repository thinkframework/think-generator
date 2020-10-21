package io.github.thinkframework.generator.swing.core.component.table;

import io.github.thinkframework.GeneratorSwingApplication;
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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 简单的根据DatabaseMetaData信息生成TableModel
 * @author lixiaobin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorSwingApplication.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorTableModelTest {

    @Autowired
    private GeneratorTableFactoryBean generatorTreeFactory;

    @Autowired
    private DataSource dataSource;

    private String tableName;

    private GeneratorTable generatorTable;

    private int count;

    @Before
    public void before() throws SQLException {
        tableName = "TEST_TABLE";
        generatorTreeFactory.setDataSource(dataSource);
        generatorTreeFactory.setTableName(tableName);
        generatorTable = generatorTreeFactory.getObject();

        ResultSet resultSet = dataSource.getConnection().getMetaData().getColumns(null,null,tableName,"%");
        while (resultSet.next()) {
            count++;
        }
    }

    @Test
    public void getModel() {
        Assert.assertNotNull(generatorTable);
        GeneratorTableModel generatorTableModel = (GeneratorTableModel) generatorTable.getModel();
        Assert.assertTrue(generatorTableModel.getRowCount() == count);
    }
}
