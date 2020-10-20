package io.github.thinkframework.swing.sql;

import io.github.thinkframework.boot.config.GeneratorContextLoader;
import io.github.thinkframework.swing.SwingConfiguration;
import io.github.thinkframework.swing.control.table.GeneratorTableModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 内部使用的一个基于CachedRowSet的DefaultTableModel
 * @see javax.sql.rowset.CachedRowSet
 * @author lixiaobin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SwingConfiguration.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorSqlTableModelTest {

    private GeneratorTableModel generatorTableModel;

    @Test
    public void emptyMethod(){

    }

}
