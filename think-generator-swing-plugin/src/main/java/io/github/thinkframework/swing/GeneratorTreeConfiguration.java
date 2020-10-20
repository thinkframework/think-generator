package io.github.thinkframework.swing;

import io.github.thinkframework.generator.Generator;
import io.github.thinkframework.generator.design.strategy.GeneratorStrategy;
import io.github.thinkframework.swing.control.tree.GeneratorTree;
import io.github.thinkframework.swing.control.tree.GeneratorTreeModel;
import io.github.thinkframework.util.FileSystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

@Slf4j
@Configuration
public class GeneratorTreeConfiguration implements BeanFactoryAware,InitializingBean {

    private BeanFactory beanFactory;

    private GeneratorTree generatorTree;

    public GeneratorTreeConfiguration(GeneratorTree generatorTree){
        this.generatorTree = generatorTree;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        generatorTree.setComponentPopupMenu(addJPopuMenu());

    }

    public JPopupMenu addJPopuMenu() {
        JPopupMenu jPopupMenu = new JPopupMenu();
        jPopupMenu.add(new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            {
                putValue(Action.NAME, "生成文件");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                generatorTable();
            }
        }));
        return jPopupMenu;
    }


    public void generatorTable() {
        String[] names = ((DefaultListableBeanFactory)beanFactory).getBeanNamesForType(GeneratorStrategy.class);
        names = new String[]{"generator"};
        String name = "generator";
        if(names.length > 1) {
            JOptionPane.showInputDialog(generatorTree, "请选择生成器", "生成器", JOptionPane.INFORMATION_MESSAGE, null, names, names[0]);
        }

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

                    beanFactory.getBean(name, Generator.class)
                        .source(beanFactory.getBean(defaultMutableTreeNode.getParent().getParent().getUserObject().toString()))
                        .target(defaultMutableTreeNode.getUserObject())
                        .generate();

                }
            }
            int confirm = JOptionPane.showConfirmDialog(generatorTree, "操作成功,是否打开输出目录?", "提示", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                FileSystemUtils.openDirectory(new File(System.getProperty("user.dir")));
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
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
