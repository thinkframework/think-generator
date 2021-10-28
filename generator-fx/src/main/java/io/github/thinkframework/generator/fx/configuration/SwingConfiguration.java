package io.github.thinkframework.generator.fx.configuration;

import io.github.thinkframework.generator.fx.view.table.GeneratorTableFactoryBean;
import io.github.thinkframework.generator.fx.view.list.GeneratorList;
import io.github.thinkframework.generator.fx.view.list.GeneratorListModel;
import io.github.thinkframework.generator.fx.view.tree.GeneratorTree;
import io.github.thinkframework.generator.fx.view.tree.GeneratorTreeModel;
import io.github.thinkframework.generator.fx.view.GeneratorMainFrame;
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
}
