package io.github.thinkframework.generator.swing.plugin.sql.component.sql;

import io.github.thinkframework.generator.swing.plugin.sql.GeneratorSqlPluginApplication;
import io.github.thinkframework.generator.test.GeneratorContextLoader;
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
 * 内部使用的一个基于CachedRowSet的DefaultTableModel
 * @see javax.sql.rowset.CachedRowSet
 * @author hdhxby
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorSqlPluginApplication.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorSqlModelTest {

    @Autowired
    private DataSource dataSource;

    private GeneratorSql generatorSql;

    private int count;

    @Before
    public void before() throws SQLException {
        String sql = "select * from test_table";
        ResultSet resultSet = dataSource.getConnection().prepareStatement(sql).executeQuery();
        while (resultSet.next()) {
            count++;
        }

        generatorSql = new GeneratorSql();
        generatorSql.setDataSource(dataSource);
        generatorSql.setSql(sql);
        generatorSql.afterPropertiesSet();
    }

    @Test
    public void getModel() {
        Assert.assertTrue(generatorSql.getModel() instanceof GeneratorSqlModel);
        Assert.assertTrue(generatorSql.getModel().getRowCount() == count);
    }

}
