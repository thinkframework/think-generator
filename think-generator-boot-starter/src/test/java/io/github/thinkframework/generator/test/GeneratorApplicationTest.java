package io.github.thinkframework.generator.test;

import io.github.thinkframework.generator.GeneratorStrategy;
import lombok.extern.slf4j.Slf4j;
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
import java.io.IOException;

/**
 * 容器测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorApplication.class)
public class GeneratorApplicationTest {


    Logger logger = LoggerFactory.getLogger(GeneratorApplicationTest.class);

    @Autowired
    private GeneratorStrategy generator;

    @Autowired
    private DataSource dataSource;

    @Before
    public void before() throws IOException {
        logger.debug("before");
//        FileUtils.forceDeleteOnExit(new File("generator_output"));
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void application() throws Exception {
        generator
            .dataSource(dataSource)
            .tableName("TEST")
            .generate();
    }

    @After
    public void after(){
        logger.debug("after");
    }
}
