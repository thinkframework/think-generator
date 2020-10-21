package io.github.thinkframework.generator.swing.core.component.sql;

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
 * 内部使用的一个基于CachedRowSet的DefaultTableModel
 * @see javax.sql.rowset.CachedRowSet
 * @author lixiaobin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorSwingApplication.class)
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
