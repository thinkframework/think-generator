package io.github.thinkframework.generator.listener;

import io.github.thinkframework.generator.design.strategy.GeneratorStrategy;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
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
    public void enterprise() throws Exception {
//        generator.generate("dataSource","TEST");
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
