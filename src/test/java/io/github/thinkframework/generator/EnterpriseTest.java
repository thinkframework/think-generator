package io.github.thinkframework.generator;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EnterpriseTest {

    Logger logger = LoggerFactory.getLogger(EnterpriseTest.class);

    @Autowired
    private Generator generator;

    @Before
    public void before() throws IOException {
        logger.debug("before");
        FileUtils.forceDeleteOnExit(new File("generator_output"));
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void enterprise() throws Exception {
        generator
            .tableName("revise_local_norm")
            .generate();
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void project() throws Exception {
        generator
            .tableName("TEST")
            .generate();
    }

    @After
    public void after(){
        logger.debug("after");
    }
}
