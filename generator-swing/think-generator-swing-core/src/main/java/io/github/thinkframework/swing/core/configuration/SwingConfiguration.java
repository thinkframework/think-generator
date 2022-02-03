package io.github.thinkframework.swing.core.configuration;

import io.github.thinkframework.swing.core.component.panel.GeneratorPanelFactoryBean;
import io.github.thinkframework.swing.core.component.table.GeneratorTableFactoryBean;
import io.github.thinkframework.swing.core.component.list.GeneratorList;
import io.github.thinkframework.swing.core.component.list.GeneratorListModel;
import io.github.thinkframework.swing.core.component.tree.GeneratorTree;
import io.github.thinkframework.swing.core.component.tree.GeneratorTreeModel;
import io.github.thinkframework.swing.core.component.frame.GeneratorMainFrame;
import io.github.thinkframework.swing.core.component.frame.GeneratorAboutFrame;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 所有配置文件
 */
@Configuration
public class SwingConfiguration {


    /**
     * 主窗体
     *
     * @return
     */
    @Bean
    public GeneratorMainFrame generatorMainFrame() {
        GeneratorMainFrame generatorMainFrame = new GeneratorMainFrame();
        generatorMainFrame.setGeneratorList(generatorList());
        generatorMainFrame.setGeneratorTree(generatorTree());
        return generatorMainFrame;
    }
    /**
     * 左侧的树
     *
     * @return
     */
    @Bean
    public GeneratorTree generatorTree() {
        GeneratorTree generatorTree = new GeneratorTree();
        generatorTree.setModel(generatorTreeModel());
        return generatorTree;
    }

    @Bean
    public GeneratorTreeModel generatorTreeModel() {
        GeneratorTreeModel generatorTreeModel = new GeneratorTreeModel();
        return generatorTreeModel;
    }

    /**
     * 元数据表格
     * @return
     */
    @Lazy
    @Bean
    public GeneratorTableFactoryBean generatorTableFactoryBean() {
        GeneratorTableFactoryBean generatorTableFactoryBean = new GeneratorTableFactoryBean();
        return generatorTableFactoryBean;
    }

    @Lazy
    @Bean
    public GeneratorList generatorList() {
        GeneratorList generatorList = new GeneratorList();
        generatorList.setModel(generatorDataSourceListModel());
        return generatorList;
    }

    @Lazy
    @Bean
    public GeneratorListModel generatorDataSourceListModel() {
        GeneratorListModel generatorListModel = new GeneratorListModel();
        return generatorListModel;
    }

    @Lazy
    @Bean
    public GeneratorPanelFactoryBean generatorPanelFactoryBean() {
        GeneratorPanelFactoryBean generatorDataSourcePanel = new GeneratorPanelFactoryBean();
        return generatorDataSourcePanel;
    }

    /**
     * 帮助窗体
     * @return
     */
    @Lazy
    @Bean
    public GeneratorAboutFrame generatorAboutFrame() {
        GeneratorAboutFrame generatorAboutFrame = new GeneratorAboutFrame();
        return generatorAboutFrame;
    }
}
