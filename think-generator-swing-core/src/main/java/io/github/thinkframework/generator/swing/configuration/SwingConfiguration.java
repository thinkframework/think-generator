package io.github.thinkframework.generator.swing.configuration;

import io.github.thinkframework.generator.swing.frame.config.GeneratorConfigureFrame;
import io.github.thinkframework.generator.swing.frame.config.GeneratorConfigurePanel;
import io.github.thinkframework.generator.swing.comp.list.GeneratorList;
import io.github.thinkframework.generator.swing.comp.list.GeneratorListModel;
import io.github.thinkframework.generator.swing.comp.table.GeneratorTableFactoryBean;
import io.github.thinkframework.generator.swing.comp.tree.GeneratorTree;
import io.github.thinkframework.generator.swing.comp.tree.GeneratorTreeModel;
import io.github.thinkframework.generator.swing.frame.control.GeneratorControlFrame;
import io.github.thinkframework.generator.swing.frame.control.GeneratorControlPanel;
import io.github.thinkframework.generator.swing.frame.about.GeneratorAboutFrame;
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
    public GeneratorControlFrame generatorControlFrame() {
        GeneratorControlFrame generatorControlFrame = new GeneratorControlFrame();
        generatorControlFrame.setGeneratorControlPanel(generatorPanel());
        return generatorControlFrame;
    }

    /**
     * 主窗体
     *
     * @return
     */
    @Bean
    public GeneratorControlPanel generatorPanel() {
        GeneratorControlPanel generatorControlPanel = new GeneratorControlPanel();
        generatorControlPanel.setGeneratorTree(generatorTree());
        return generatorControlPanel;
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

    /**
     * 设置窗体
     *
     * @return
     */
    @Lazy
    @Bean
    public GeneratorConfigureFrame generatorListFrame() {
        GeneratorConfigureFrame generatorConfigureFrame = new GeneratorConfigureFrame();
        generatorConfigureFrame.setGeneratorList(generatorList());
        generatorConfigureFrame.setGeneratorConfigurePanel(generatorConfigurePanel());
        return generatorConfigureFrame;
    }

    @Lazy
    @Bean
    public GeneratorList generatorList() {
        GeneratorList generatorList = new GeneratorList();
        generatorList.setModel(generatorListModel());
        return generatorList;
    }

    @Lazy
    @Bean
    public GeneratorListModel generatorListModel() {
        GeneratorListModel generatorListModel = new GeneratorListModel();
        return generatorListModel;
    }

    @Lazy
    @Bean
    public GeneratorConfigurePanel generatorConfigurePanel() {
        GeneratorConfigurePanel generatorConfigurePanel = new GeneratorConfigurePanel();
        return generatorConfigurePanel;
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
