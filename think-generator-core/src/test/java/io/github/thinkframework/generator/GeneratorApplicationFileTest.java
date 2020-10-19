package io.github.thinkframework.generator;

import io.github.thinkframework.generator.design.strategy.impl.GeneratorFile;
import io.github.thinkframework.generator.util.FileUtil;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
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

        FileUtil.deleteIfExists(new File("generator"));

        generator.getProperties().getStragegy().setClazz(GeneratorFile.class.getName());
//        generator.getProperties().getStragegy().setResponsibilitys(
//            Stream.of(ConfigurationGeneratorProvider.class.getName(), classGeneratorProvider.class.getName())
//                .collect(Collectors.toList()));
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