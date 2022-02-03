package io.github.thinkframework.swing.plugin.generator.configuration;

import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import io.github.thinkframework.generator.core.GeneratorFactoryBean;
import io.github.thinkframework.generator.core.util.FileUtils;
import io.github.thinkframework.swing.core.component.tree.GeneratorTree;
import io.github.thinkframework.swing.core.component.tree.GeneratorTreeModel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

@Configuration
public class GeneratorPluginConfiguration implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private GeneratorProperties generatorProperties;

    private GeneratorTree generatorTree;

    private GeneratorFactoryBean generatorFactoryBean;

    public GeneratorPluginConfiguration(GeneratorFactoryBean generatorFactoryBean,GeneratorProperties generatorProperties,GeneratorTree generatorTree){
        this.generatorFactoryBean = generatorFactoryBean;
        this.generatorProperties = generatorProperties;
        this.generatorTree = generatorTree;
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
                    putValue(Action.NAME, "生成文件");
                }
                @Override
                public void actionPerformed(ActionEvent e) {
                    generatorTable(e);
                }
            }));
        });
    }

    /**
     * 根据表生成文件
     */
    public void generatorTable(ActionEvent e) {
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

                    generatorFactoryBean.getObject()
                        .generate(applicationContext.getBean(defaultMutableTreeNode.getParent().getParent().getUserObject().toString()),
                            defaultMutableTreeNode.getUserObject());
                }
            }
            int confirm = JOptionPane.showConfirmDialog(generatorTree, "操作成功,是否打开输出目录?", "提示", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                FileUtils.openDirectory(new File(System.getProperty("user.dir")));
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

}
