package org.think.generator.config;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ActiveProfiles("core")
public class CoreNamespaceHandlerTest extends AbstractJUnit4SpringContextTests {
//    @Autowired
//    private GeneratorFacade generator;
//    @Test
//    public void test(){
//        assertNotNull(generator);
//    }
}
