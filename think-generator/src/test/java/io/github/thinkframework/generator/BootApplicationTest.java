package io.github.thinkframework.generator;

import org.junit.After;
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

/**
 * 容器测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GeneratorApplication.class})
public class BootApplicationTest {

    Logger logger = LoggerFactory.getLogger(BootApplicationTest.class);

    @Autowired
    private Generator generator;


    @Before
    public void before() {
        logger.debug("before");
        if(new File("generator_output").exists()){
            new File("generator_output").delete();
        }
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void application() throws Exception {
        generator
            .dataSourceName("dataSource")
            .tableName("TEST")
            .generate();
    }

    @After
    public void after(){
        logger.debug("after");
    }
}
