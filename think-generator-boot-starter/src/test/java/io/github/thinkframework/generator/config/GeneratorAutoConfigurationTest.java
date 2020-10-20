package io.github.thinkframework.generator.config;

import io.github.thinkframework.generator.Generator;
import io.github.thinkframework.generator.GeneratorApplication;
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
@SpringBootTest(classes = GeneratorApplication.class)
public class GeneratorAutoConfigurationTest {

    @Autowired
    private GeneratorProperties generatorProperties;

    @Autowired
    private Generator generator;

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
        Assert.assertNotNull(generatorProperties);
        Assert.assertNotNull(generator);
    }
}
