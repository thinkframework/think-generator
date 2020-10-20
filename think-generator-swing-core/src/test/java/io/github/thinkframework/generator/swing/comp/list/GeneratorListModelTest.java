package io.github.thinkframework.generator.swing.comp.list;

import io.github.thinkframework.SwingApplication;
import io.github.thinkframework.boot.config.GeneratorContextLoader;
import io.github.thinkframework.generator.swing.configuration.SwingConfiguration;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SwingApplication.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorListModelTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private GeneratorListModel generatorListModel;

    private int length;

    @Before
    public void before(){
        length = applicationContext.getBeanNamesForType(DataSource.class).length;
    }

    @Test
    public void getSize() {
        MatcherAssert.assertThat(generatorListModel.getSize(),greaterThanOrEqualTo(length));
    }

    @Test
    public void getElementAt() {
        assertNotNull(generatorListModel.getElementAt(0));
    }
}
