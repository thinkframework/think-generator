package io.github.thinkframework.generator.swing.plugin.sql.configuration;

import io.github.thinkframework.generator.core.GeneratorFactoryBean;
import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import io.github.thinkframework.generator.swing.core.component.tree.GeneratorTree;
import io.github.thinkframework.generator.swing.core.component.tree.GeneratorTreeModel;
import io.github.thinkframework.generator.swing.core.frame.main.GeneratorMainFrame;
import io.github.thinkframework.generator.swing.plugin.sql.component.sql.GeneratorSql;
import io.github.thinkframework.generator.swing.plugin.sql.frame.sql.GeneratorSqlPanel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;

@Configuration
public class GeneratorSqlPluginConfiguration implements ApplicationContextAware, ResourceLoaderAware, MessageSourceAware,InitializingBean {

    private ApplicationContext applicationContext;

    private ResourceLoader resourceLoader;

    private MessageSource messageSource;

    private GeneratorProperties generatorProperties;

    GeneratorMainFrame generatorMainFrame;

    GeneratorTree generatorTree;

    GeneratorFactoryBean generatorFactoryBean;

    public GeneratorSqlPluginConfiguration(GeneratorFactoryBean generatorFactoryBean,GeneratorProperties generatorProperties,GeneratorMainFrame generatorMainFrame,GeneratorTree generatorTree){
        this.generatorFactoryBean = generatorFactoryBean;
        this.generatorProperties = generatorProperties;
        this.generatorMainFrame = generatorMainFrame;
        this.generatorTree = generatorTree;
    }

    @Bean
    @Lazy
    public GeneratorSqlPanel generatorSqlPanel(){
        return new GeneratorSqlPanel();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        EventQueue.invokeLater(() -> {
            if(generatorTree.getComponentPopupMenu() == null){
                generatorTree.setComponentPopupMenu(new JPopupMenu());
            }
            generatorTree.getComponentPopupMenu().add(new JMenuItem(new AbstractAction() {
                private static final long serialVersionUID = 1L;

                {
                    putValue(Action.NAME, "执行sql");
                }
                @Override
                public void actionPerformed(ActionEvent e) {
                    generatorSql();
                }
            }));
        });
    }

    /**
     * 根据表生成文件
     */
    public void generatorSql() {
        TreePath[] treePaths = generatorTree.getSelectionPaths();
        try {
            if (treePaths == null) {
                return;
            }
            for (int i = 0; i < treePaths.length; i++) {
                TreePath treePath = treePaths[i];
                int count = treePath.getPathCount();
                for (int j = 1; j < count; j++) {
                    if (j != 3) {
                        continue;
                    }
                    GeneratorTreeModel.GeneratorTreeNode defaultMutableTreeNode = (GeneratorTreeModel.GeneratorTreeNode) treePath
                        .getPathComponent(j);

                    EventQueue.invokeLater(() -> {
                        GeneratorSqlPanel generatorSqlPanel = new GeneratorSqlPanel();
                        generatorSqlPanel.setDataSource(applicationContext.getBean(defaultMutableTreeNode.getParent().getParent().getUserObject().toString(), DataSource.class));
                        generatorSqlPanel.afterPropertiesSet();
                        generatorMainFrame.getGeneratorMainPanel().getCenterTabbedPane()
                            .add(defaultMutableTreeNode.getParent().getParent().getUserObject().toString()+" "+generatorMainFrame.getGeneratorMainPanel().getCenterTabbedPane().getTabCount(),
                            generatorSqlPanel);
                    });
                }
            }
        } catch (Exception ex) {
            EventQueue.invokeLater(() -> {
                JOptionPane.showMessageDialog(
                    generatorTree,
                    ex.getMessage(),
                    "错误",
                    JOptionPane.ERROR_MESSAGE
                );
            });
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
