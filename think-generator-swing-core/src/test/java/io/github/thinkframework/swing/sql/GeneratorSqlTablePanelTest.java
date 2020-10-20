package io.github.thinkframework.swing.sql;

import io.github.thinkframework.boot.config.GeneratorContextLoader;
import io.github.thinkframework.swing.SwingConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 扩充了工具条
 * @author lixiaobin
 * @since 2017/3/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SwingConfiguration.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorSqlTablePanelTest {

    @Autowired
    private GeneratorSqlTablePanel generatorSqlTablePanel;

    @Test
    public void emptyMethod(){

    }
}
