package io.github.thinkframework.generator.swing.core.configuration;

import io.github.thinkframework.fx.core.control.MainController;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 容器测试
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = FxApplication.class)
public class GeneratorSwingAutoConfigurationTest {

    @Autowired
    private MainController mainController;

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
        Assert.assertNotNull(mainController);
    }
}
