package io.github.thinkframework.swing.core.component.tree;

import io.github.thinkframework.GeneratorSwingApplication;
import io.github.thinkframework.boot.config.GeneratorContextLoader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.util.Enumeration;

import static org.junit.Assert.assertNotNull;

/**
 * 简单的根据DatabaseMetaData信息生成TableModel
 * @author hdhxby
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorSwingApplication.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorTreeModelTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private GeneratorTreeModel generatorTreeModel;

    private int length;

    public void before(){
        length = applicationContext.getBeanNamesForType(DataSource.class).length;
    }
    @Test
    public void getSize() {
        GeneratorTreeModel.GeneratorTreeNode generatorTreeNode= (GeneratorTreeModel.GeneratorTreeNode)generatorTreeModel.getRoot();
        if(length > 0) {
            Enumeration enumeration = generatorTreeNode.children();
            while (enumeration.hasMoreElements()){
                Assert.assertTrue(enumeration.nextElement() instanceof GeneratorTreeModel);
            }
        }
    }
}
