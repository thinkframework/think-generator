package io.github.thinkframework.generator;

import io.github.thinkframework.generator.util.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;

/**
 * 容器测试
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class GeneratorApplicationXmlTest {

    Logger logger = LoggerFactory.getLogger(GeneratorApplicationXmlTest.class);

    @Autowired
    private Generator generator;

    @Autowired
    private DataSource dataSource;

    @Before
    public void before() throws IOException {
        FileUtil.deleteIfExists(new File("generator"));
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void application() throws Exception {
        generator
            .source(dataSource)
            .target("TEST")
            .generate();
    }
}
