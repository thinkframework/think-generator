package io.github.thinkframework.swing.control.table.meta;

import io.github.thinkframework.boot.config.GeneratorContextLoader;
import io.github.thinkframework.swing.SwingConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SwingConfiguration.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorTablePanelTest {

    @Test
    public void emptyMethod(){

    }
}
