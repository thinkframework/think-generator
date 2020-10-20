package io.github.thinkframework.swing.sql;

import io.github.thinkframework.boot.config.GeneratorContextLoader;
import io.github.thinkframework.swing.SwingConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 内部使用的,基于SqlTableModel的可编辑的JTable
 * @see GeneratorSqlTableModel
 * @author lixiaobin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SwingConfiguration.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorSqlTableTest {

    @Autowired
    private GeneratorSqlTable generatorSqlTable;

    @Test
    public void getModel() {
        Assert.assertTrue(generatorSqlTable.getModel() instanceof GeneratorSqlTableModel);
    }
}
