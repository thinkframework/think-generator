package io.github.thinkframework.swing;

import io.github.thinkframework.swing.config.GeneratorConfigureFrame;
import io.github.thinkframework.swing.config.GeneratorConfigurePanel;
import io.github.thinkframework.swing.config.list.GeneratorList;
import io.github.thinkframework.swing.config.list.GeneratorListModel;
import io.github.thinkframework.swing.control.GeneratorControlFrame;
import io.github.thinkframework.swing.control.GeneratorControlPanel;
import io.github.thinkframework.swing.control.table.GeneratorTableFactoryBean;
import io.github.thinkframework.swing.control.tree.GeneratorTree;
import io.github.thinkframework.swing.control.tree.GeneratorTreeModel;
import io.github.thinkframework.swing.other.GeneratorAboutFrame;
import io.github.thinkframework.swing.other.GeneratorLogFrame;
import io.github.thinkframework.swing.sql.GeneratorSqlPanel;
import io.github.thinkframework.swing.sql.GeneratorSqlTable;
import io.github.thinkframework.swing.sql.GeneratorSqlTableModel;
import io.github.thinkframework.swing.sql.GeneratorSqlTablePanel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

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
     * SQL执行窗口
     * @return
     */
    @Scope("prototype")
    @Lazy
    @Bean
    public GeneratorSqlPanel generatorSqlPanel() {
        GeneratorSqlPanel generatorSqlPanel = new GeneratorSqlPanel();
        generatorSqlPanel.setGeneratorSqlTablePanel(generatorSqlTablePanel());
        return generatorSqlPanel;
    }

    @Scope("prototype")
    @Lazy
    @Bean
    public GeneratorSqlTablePanel generatorSqlTablePanel() {
        GeneratorSqlTablePanel generatorSqlTablePanel = new GeneratorSqlTablePanel();
        generatorSqlTablePanel.setGeneratorSqlTable(generatorSqlTable());
        return generatorSqlTablePanel;
    }

    @Scope("prototype")
    @Lazy
    @Bean
    public GeneratorSqlTable generatorSqlTable() {
        GeneratorSqlTable generatorSqlTable = new GeneratorSqlTable();
        generatorSqlTable.setModel(new GeneratorSqlTableModel());
        return generatorSqlTable;
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

    /**
     * 日志窗体
     * @return
     */
    @Lazy
    @Bean
    public GeneratorLogFrame generatorLogFrame() {
        GeneratorLogFrame generatorLogFrame = new GeneratorLogFrame();
        return generatorLogFrame;
    }
}
