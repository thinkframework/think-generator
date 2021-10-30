package io.github.thinkframework.generator.baens.factory.xml;

import io.github.thinkframework.generator.beans.factory.GeneratorTableFactoryBean;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * @author hdhxby
 * @since 2017/3/24
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext-generator.xml")
public class GeneratorNamespaceHandlerTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private GeneratorTableFactoryBean generatorTableFactoryBean;

    @Test
    public void test(){
        assertNotNull(generatorTableFactoryBean);
    }
}
