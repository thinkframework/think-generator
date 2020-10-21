package io.github.thinkframework.generator.swing.component.list;

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
 * 列表控制类
 * @author lixiaobin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorSwingApplication.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorListTest {

    @Autowired
    private GeneratorList generatorList;

    @Test
    public void getGeneratorListModel() {
        Assert.assertTrue(generatorList.getModel() instanceof GeneratorListModel);
    }
}
