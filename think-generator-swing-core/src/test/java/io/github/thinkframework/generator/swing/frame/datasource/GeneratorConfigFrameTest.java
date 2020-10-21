package io.github.thinkframework.generator.swing.frame.datasource;

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
public class GeneratorConfigFrameTest {

    @Autowired
    private GeneratorDataSourceFrame generatorDataSourceFrame;

    @Test
    public void getModel() {
        Assert.assertFalse(generatorDataSourceFrame.isVisible());
    }
}
