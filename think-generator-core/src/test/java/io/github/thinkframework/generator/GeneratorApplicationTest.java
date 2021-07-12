package io.github.thinkframework.generator;

import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import io.github.thinkframework.generator.core.Generator;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * 容器测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorApplication.class)
public class GeneratorApplicationTest {

    Logger logger = LoggerFactory.getLogger(GeneratorApplicationTest.class);

    @Autowired
    private GeneratorProperties generatorConfiguration;

    @Autowired
    private Generator generator;

    @Autowired
    private DataSource dataSource;

    @Before
    public void before() {
        generator.clear();
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        generator.generate(dataSource,"%");
    }
}
