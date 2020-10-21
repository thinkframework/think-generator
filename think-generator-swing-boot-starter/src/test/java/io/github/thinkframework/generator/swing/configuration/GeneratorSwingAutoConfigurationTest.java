package io.github.thinkframework.generator.swing.configuration;

import io.github.thinkframework.generator.swing.SwingApplication;
import io.github.thinkframework.generator.swing.frame.main.GeneratorMainFrame;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 容器测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SwingApplication.class)
public class GeneratorSwingAutoConfigurationTest {

    @Autowired
    private GeneratorMainFrame generatorMainFrame;

    @BeforeClass
    public static void before(){
        System.setProperty("java.awt.headless", "false");
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void application() throws Exception {
        Assert.assertNotNull(generatorMainFrame);
    }
}
