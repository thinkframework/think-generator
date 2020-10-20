package io.github.thinkframework.swing.config.list;

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
 * 列表控制类
 * @author lixiaobin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SwingConfiguration.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorListTest {

    @Autowired
    private GeneratorList generatorList;

    @Test
    public void getGeneratorListModel() {
        Assert.assertTrue(generatorList.getModel() instanceof GeneratorListModel);
    }
}
