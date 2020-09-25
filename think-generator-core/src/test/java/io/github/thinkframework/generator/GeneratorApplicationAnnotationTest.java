package io.github.thinkframework.generator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

/**
 * 容器测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorApplication.class)
public class GeneratorApplicationAnnotationTest {

    Logger logger = LoggerFactory.getLogger(GeneratorApplicationAnnotationTest.class);

    @Autowired
    private Generator generator;

    @Autowired
    private DataSource dataSource;

    @Before
    public void before() throws IOException {
        logger.debug("before");
        Files.walk(new File("generator_output").toPath())
            .map(Path::toFile).
            sorted(Comparator.reverseOrder())
            .forEach(File::deleteOnExit);
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void application() throws Exception {
        generator
            .source(dataSource)
            .target("%")
            .generate();
    }

    @After
    public void after(){
        logger.debug("after");
    }

}
