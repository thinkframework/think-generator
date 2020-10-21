package io.github.thinkframework.generator.swing.plugin.configuration;

import io.github.thinkframework.generator.swing.GeneratorPluginApplication;
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
@SpringBootTest(classes = GeneratorPluginApplication.class)
public class GeneratorPluginConfigurationTest {

    @Autowired
    private GeneratorPluginConfiguration generatorPluginConfiguration;

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
        Assert.assertNotNull(generatorPluginConfiguration.generatorTree);
        Assert.assertNotNull(generatorPluginConfiguration.generatorFactoryBean);
    }
}
