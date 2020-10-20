package io.github.thinkframework.generator.swing.frame.config;

import io.github.thinkframework.SwingApplication;
import io.github.thinkframework.boot.config.GeneratorContextLoader;
import io.github.thinkframework.generator.swing.configuration.SwingConfiguration;
import io.github.thinkframework.generator.swing.frame.about.GeneratorAboutFrame;
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
@SpringBootTest(classes = SwingApplication.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorConfigFrameTest {

    @Autowired
    private GeneratorConfigureFrame generatorConfigureFrame;

    @Test
    public void getModel() {
        Assert.assertFalse(generatorConfigureFrame.isVisible());
    }
}
