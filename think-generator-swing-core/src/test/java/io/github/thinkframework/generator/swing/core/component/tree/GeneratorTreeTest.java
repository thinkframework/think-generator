package io.github.thinkframework.generator.swing.core.component.tree;

import io.github.thinkframework.GeneratorSwingApplication;
import io.github.thinkframework.boot.config.GeneratorContextLoader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorSwingApplication.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorTreeTest {

    @Autowired
    private GeneratorTree generatorTree;

    @Test
    public void getModel() {
        Assert.assertTrue(generatorTree.getModel() instanceof GeneratorTreeModel);
    }
}
