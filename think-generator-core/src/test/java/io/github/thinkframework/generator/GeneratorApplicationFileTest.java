package io.github.thinkframework.generator;

import io.github.thinkframework.generator.core.GeneratorFactoryBean;
import io.github.thinkframework.generator.core.design.strategy.impl.GeneratorFile;
import io.github.thinkframework.generator.core.util.FileUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

/**
 * 容器测试
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorApplication.class)
public class GeneratorApplicationFileTest {

    Logger logger = LoggerFactory.getLogger(GeneratorApplicationTest.class);

    @Autowired
    private GeneratorFactoryBean generator;

    @Before
    public void before() throws IOException {
        logger.debug("before");

        FileUtil.deleteIfExists(new File("generator"));

        generator.getProperties().getStragegy().setClazz(GeneratorFile.class.getName());
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void application() throws Exception {
        generator.getObject().source(new File("c:\\opt\\java")).target("Example.class").generate();
    }
}
