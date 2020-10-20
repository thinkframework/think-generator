package io.github.thinkframework.swing.control.tree;

import io.github.thinkframework.boot.config.GeneratorContextLoader;
import io.github.thinkframework.swing.SwingConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 简单的根据DatabaseMetaData信息生成TableModel
 * @author lixiaobin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SwingConfiguration.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorTreeModelTest {

    @Test
    public void emptyMethod(){

    }
}
