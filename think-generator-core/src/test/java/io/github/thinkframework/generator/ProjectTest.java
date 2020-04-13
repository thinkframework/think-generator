package io.github.thinkframework.generator;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ProjectTest {

    Logger logger = LoggerFactory.getLogger(ProjectTest.class);

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
            .tableName("bim5d_cost_cztj_summary")
            .generate();
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void project() throws Exception {
        generator
            .tableName("bim5d_cost_cbkm_bidsummary")
            .generate();

        generator
            .tableName("bim5d_cost_geqsummary")
            .generate();

        generator
            .tableName("bim5d_cost_geqcbkm_contrast")
            .generate();

        generator
            .tableName("bim5d_cost_geqnewcbkm_contrast")
            .generate();

        generator
            .tableName("bim5d_cost_geqcbkm_contrastdetail")
            .generate();

    }

    @After
    public void after(){
        logger.debug("after");
    }
}
