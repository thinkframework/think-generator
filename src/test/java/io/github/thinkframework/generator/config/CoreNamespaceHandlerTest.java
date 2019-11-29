package io.github.thinkframework.generator.config;

import io.github.thinkframework.generator.Generator;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CoreNamespaceHandlerTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private Generator generator;

    @Test
    public void test(){
        assertNotNull(generator);
    }
}
