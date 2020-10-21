package io.github.thinkframework.generator.core.xml;

import io.github.thinkframework.generator.core.GeneratorFactoryBean;
import io.github.thinkframework.generator.core.config.GeneratorProperties;
import io.github.thinkframework.generator.core.design.strategy.GeneratorStrategy;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/generator.xml")
public class GeneratorNamespaceHandlerTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private GeneratorFactoryBean generatorFactoryBean;

    @Autowired
    private GeneratorProperties generatorProperties;

    @Test
    public void test(){
        assertNotNull(generatorFactoryBean);
    }
}
