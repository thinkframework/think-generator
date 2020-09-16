package io.github.thinkframework.generator;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 容器测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorApplication.class)
public class GeneratorApplicationFileTest {

    Logger logger = LoggerFactory.getLogger(GeneratorApplicationAnnotationTest.class);

    @Autowired
    private GeneratorFactoryBean generator;

    @Before
    public void before() throws IOException {
        logger.debug("before");
        FileUtils.forceDeleteOnExit(new File("generator_output"));

        generator.getProperties().getStragegy().setClazz("io.github.thinkframework.generator.strategy.GeneratorFile");
        generator.getProperties().getStragegy().setResponsibilitys(
            Stream.of("io.github.thinkframework.generator.provider.ConfigurationGeneratorProvider",
            "io.github.thinkframework.generator.provider.ClassGeneratorProvider")
                .collect(Collectors.toList()));
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void application() throws Exception {
        generator.getObject().source(new File("c:\\opt\\java")).target("Example.class").generate();
    }

    @After
    public void after(){
        logger.debug("after");
    }

    class Person{
        private String id;
        private String name;
    }
}
