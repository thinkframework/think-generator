package io.github.thinkframework.generator.listener;

import io.github.thinkframework.generator.GeneratorStrategy;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ListenerTest {
    Logger logger = LoggerFactory.getLogger(ListenerTest.class);

    @Autowired
    private GeneratorStrategy generator;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

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
            .tableName("bim5d_cost_cztj_summary")
            .generate();
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void project() throws Exception {
        try {
            applicationEventPublisher.publishEvent(new PayloadApplicationEvent("generator", new HashSet<>()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void after(){
        logger.debug("after");
    }
}
