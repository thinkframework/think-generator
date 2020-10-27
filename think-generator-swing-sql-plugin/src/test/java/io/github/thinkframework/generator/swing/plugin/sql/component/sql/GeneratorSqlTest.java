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
import java.sql.SQLException;

/**
 * 内部使用的,基于SqlTableModel的可编辑的JTable
 * @see GeneratorSqlModel
 * @author hdhxby
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorSqlPluginApplication.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorSqlTest {

    @Autowired
    private DataSource dataSource;

    private GeneratorSql generatorSql;

    @Before
    public void before() throws SQLException {
        generatorSql = new GeneratorSql();
        generatorSql.setDataSource(dataSource);
        generatorSql.setSql("select * from test_table");
        generatorSql.afterPropertiesSet();
    }

    @Test
    public void getModel() {
        Assert.assertTrue(generatorSql.getModel() instanceof GeneratorSqlModel);
    }
}
