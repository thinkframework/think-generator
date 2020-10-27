package io.github.thinkframework.generator.swing.plugin.sql.configuration;

import io.github.thinkframework.generator.swing.plugin.sql.GeneratorSqlPluginApplication;
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
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorSqlPluginApplication.class)
public class GeneratorSqlPluginConfigurationTest {

    @Autowired
    private GeneratorSqlPluginConfiguration generatorSqlPluginConfiguration;

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
        Assert.assertNotNull(generatorSqlPluginConfiguration.generatorTree);
        Assert.assertNotNull(generatorSqlPluginConfiguration.generatorFactoryBean);
    }
}
