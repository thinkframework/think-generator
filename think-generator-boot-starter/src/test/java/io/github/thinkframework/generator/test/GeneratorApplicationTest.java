package io.github.thinkframework.generator.test;

import io.github.thinkframework.generator.GeneratorTable;
import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
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

    @Autowired
    private ApplicationContext applicationContext;

//    @Autowired
    private GeneratorTable generator;

    @Autowired
    private DataSource dataSource;

    @Before
    public void before() throws IOException {
        log.debug("before");
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
        log.debug("after");
    }
}
