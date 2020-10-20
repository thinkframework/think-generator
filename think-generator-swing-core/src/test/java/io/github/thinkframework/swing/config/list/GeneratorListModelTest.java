package io.github.thinkframework.swing.config.list;

import io.github.thinkframework.boot.config.GeneratorContextLoader;
import io.github.thinkframework.swing.SwingConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SwingConfiguration.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorListModelTest {
    @Autowired
    private GeneratorListModel generatorListModel;

    @Test
    public void getSize() {
        assertThat(generatorListModel.getSize(),greaterThanOrEqualTo(1));
    }

    @Test
    public void getElementAt() {
        assertNotNull(generatorListModel.getElementAt(0));
    }
}
