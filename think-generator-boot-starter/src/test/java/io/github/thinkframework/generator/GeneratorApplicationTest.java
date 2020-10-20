package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 容器测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorApplication.class)
public class GeneratorApplicationTest {


    Logger logger = LoggerFactory.getLogger(GeneratorApplicationTest.class);

    @Autowired
    private GeneratorProperties generatorProperties;

    @Autowired
    private Generator generator;

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
