package io.github.thinkframework.generator.swing.configuration;

import io.github.thinkframework.generator.swing.frame.datasource.GeneratorDataSourceFrame;
import io.github.thinkframework.generator.swing.frame.datasource.GeneratorDataSourcePanel;
import io.github.thinkframework.generator.swing.component.list.GeneratorList;
import io.github.thinkframework.generator.swing.component.list.GeneratorListModel;
import io.github.thinkframework.generator.swing.component.table.GeneratorTableFactoryBean;
import io.github.thinkframework.generator.swing.component.tree.GeneratorTree;
import io.github.thinkframework.generator.swing.component.tree.GeneratorTreeModel;
import io.github.thinkframework.generator.swing.frame.main.GeneratorMainFrame;
import io.github.thinkframework.generator.swing.frame.main.GeneratorMainPanel;
import io.github.thinkframework.generator.swing.frame.about.GeneratorAboutFrame;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 所有配置文件
 */
@Configuration
public class SwingConfiguration {

    @Configuration
    class ControlCinfiguration {

        /**
         * 主窗体
         *
         * @return
         */
        @Bean
        public GeneratorMainFrame generatorControlFrame() {
            GeneratorMainFrame generatorMainFrame = new GeneratorMainFrame();
            generatorMainFrame.setGeneratorControlPanel(generatorControlPanel());
            return generatorMainFrame;
        }

        /**
         * 主窗体
         *
         * @return
         */
        @Bean
        public GeneratorMainPanel generatorControlPanel() {
            GeneratorMainPanel generatorMainPanel = new GeneratorMainPanel();
            generatorMainPanel.setGeneratorTree(generatorTree());
            return generatorMainPanel;
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
    }


    @Configuration
    class DataSourceConfiguration {

        /**
         * 设置窗体
         *
         * @return
         */
        @Lazy
        @Bean
        public GeneratorDataSourceFrame generatorDataSourceFrame() {
            GeneratorDataSourceFrame generatorDataSourceFrame = new GeneratorDataSourceFrame();
            generatorDataSourceFrame.setGeneratorList(generatorDataSourceList());
            generatorDataSourceFrame.setGeneratorConfigurePanel(generatorDataSourcePanel());
            return generatorDataSourceFrame;
        }

        @Lazy
        @Bean
        public GeneratorList generatorDataSourceList() {
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
        public GeneratorDataSourcePanel generatorDataSourcePanel() {
            GeneratorDataSourcePanel generatorDataSourcePanel = new GeneratorDataSourcePanel();
            return generatorDataSourcePanel;
        }
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
