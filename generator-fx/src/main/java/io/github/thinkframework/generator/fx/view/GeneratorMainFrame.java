package io.github.thinkframework.generator.fx.view;

import io.github.thinkframework.generator.fx.view.list.GeneratorList;
import io.github.thinkframework.generator.fx.view.table.GeneratorTable;
import io.github.thinkframework.generator.fx.view.table.GeneratorTableFactoryBean;
import io.github.thinkframework.generator.fx.view.tree.GeneratorTree;
import io.github.thinkframework.generator.fx.view.tree.GeneratorTreeModel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.*;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hdhxby
 * @email hdhxby@qq.com
 */
public class GeneratorMainFrame extends JFrame implements ApplicationContextAware, ResourceLoaderAware, MessageSourceAware, InitializingBean {
    private static final long serialVersionUID = 1L;

    private final Integer WIDTH = 1024, HEIGHT = 768;

    private ApplicationContext applicationContext;

    private ResourceLoader resourceLoader;

    private MessageSource messageSource;

    private GeneratorList generatorList;

    private GeneratorTree generatorTree;

    private JTabbedPane centerTabbedPane = new JTabbedPane(JTabbedPane.TOP);

    public GeneratorMainFrame() throws HeadlessException {
        setTitle("Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭
        setSize(WIDTH, HEIGHT);// 设置大小
        setResizable(true);//改变大小
        setLocationRelativeTo(null);//居中
        setJMenuBar(menuBar());
    }

    @Override
    public void afterPropertiesSet() {
        EventQueue.invokeLater(() -> add(centerPanel()));
    }

    /**
     * 初始化菜单
     *
     * @return 菜单
     */
    public JMenuBar menuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(addFile());
        jMenuBar.add(addTheme());
        return jMenuBar;
    }
    /**
     * 文件
     * @return 文件菜单
     */
    public JMenu addFile() {
        JMenu mnFile = new JMenu("File(F)");
        mnFile.setMnemonic('F');

        JMenuItem mnExitItem = new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            {
                putValue(Action.NAME, "Exit");
            }

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnFile.add(mnExitItem);
        return mnFile;
    }

    public JMenu addTheme() {
        JMenu mnTheme = new JMenu("Theme(T)");
        mnTheme.setMnemonic('T');
        ButtonGroup buttonGroup = new ButtonGroup();
        UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
        for (int i = 0; i < lookAndFeelInfos.length; i++) {
            final UIManager.LookAndFeelInfo lookAndFeelInfo = lookAndFeelInfos[i];
            JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(new AbstractAction(lookAndFeelInfo.getName()) {
                private static final long serialVersionUID = 1L;

                public void actionPerformed(ActionEvent e) {
                    try {
                        if (System.getProperty("os.name").contains("")) {

                        }
                        UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                        SwingUtilities.updateComponentTreeUI(GeneratorMainFrame.this);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            if (UIManager.getLookAndFeel().getName().equals(lookAndFeelInfo.getName())) {
                jCheckBoxMenuItem.setSelected(true);
            }
            buttonGroup.add(jCheckBoxMenuItem);
            mnTheme.add(jCheckBoxMenuItem);
        }
        return mnTheme;
    }


    public JSplitPane centerPanel() {
        JSplitPane jSplitPane = new JSplitPane();
        jSplitPane.setLeftComponent(leftCenterPanel());
        jSplitPane.setRightComponent(rightCenterPanel());
        jSplitPane.setOneTouchExpandable(true);
        jSplitPane.setContinuousLayout(true);
        return jSplitPane;
    }

    public JTabbedPane leftCenterPanel() {
        JTabbedPane westTabbedPane = new JTabbedPane();
        westTabbedPane.addTab("连接信息", null, new JScrollPane(generatorList), null);
        westTabbedPane.addTab("表信息", null, new JScrollPane(generatorTree), null);
        return westTabbedPane;
    }

    public JTabbedPane rightCenterPanel() {
        add(centerTabbedPane, BorderLayout.CENTER);
        centerTabbedPane.addTab("起始页", null, initFirstTable(), null);
        return centerTabbedPane;
    }

    private JEditorPane initFirstTable() {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setText(
            "SQL工具箱,提供了表结构预览及代码生成功能.\n" +
            "数据源的配置基于spring的配置文件,可自行修改.\n" +
            "生成器基于自定义命令空间,可参考xsd修改." );
        return editorPane;
    }

    private JTabbedPane addDatasourcePanel(String dataSourceName) {
        int count = centerTabbedPane.getTabCount();
        boolean exists = false;
        for (int i = 0; i < count; i++) {
            if (centerTabbedPane.getTitleAt(i).equals(dataSourceName)) {
                centerTabbedPane.setSelectedIndex(i);
                exists = true;
                break;
            }
        }
        if (!exists) {
            EventQueue.invokeLater(() ->{
//                todo 删除掉,没用了
//                //设置FactoryBean
//                GeneratorPanelFactoryBean panelFactoryBean = applicationContext.getBean(GeneratorPanelFactoryBean.class);
//                panelFactoryBean.setDataSourceName(dataSourceName);
//                JScrollPane jScrollPane = new JScrollPane(panelFactoryBean.getObject());
//                centerTabbedPane.addTab(dataSourceName, null, jScrollPane, null);
//                centerTabbedPane.setSelectedComponent(jScrollPane);
            });
        }
        return centerTabbedPane;
    }

    private JTabbedPane addTablePanel(String dataSourceName, String tableName) {
        int count = centerTabbedPane.getTabCount();
        boolean exists = false;
        for (int i = 0; i < count; i++) {
            if (centerTabbedPane.getTitleAt(i).equals(tableName)) {
                centerTabbedPane.setSelectedIndex(i);
                exists = true;
                break;
            }
        }
        if (!exists) {
            EventQueue.invokeLater(() ->{
//                todo 删除,没用了
//                //设置FactoryBean
//                GeneratorTableFactoryBean generatorTableFactoryBean = applicationContext.getBean(GeneratorTableFactoryBean.class);
//                generatorTableFactoryBean.setDataSource(getDataSource(dataSourceName));
//                generatorTableFactoryBean.setTableName(tableName);
//                //获取GeneratorTable
//                GeneratorTable generatorTabbedPanel = applicationContext.getBean(GeneratorTable.class);
//                JScrollPane jScrollPane = new JScrollPane(generatorTabbedPanel);
//                centerTabbedPane.addTab(tableName, null, jScrollPane, null);
//                centerTabbedPane.setSelectedComponent(jScrollPane);
            });
        }
        return centerTabbedPane;
    }

    protected DataSource getDataSource(String id) {
        return applicationContext.getBean(id, DataSource.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Assert.notNull(applicationContext, "");
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

    public GeneratorList getGeneratorList() {
        return generatorList;
    }

    public void setGeneratorList(GeneratorList generatorList) {
        generatorList.addListSelectionListener(e -> {
            addDatasourcePanel(((GeneratorList)e.getSource()).getSelectedValue().toString());
        });
        this.generatorList = generatorList;
    }

    public GeneratorTree getGeneratorTree() {
        return generatorTree;
    }

    public void setGeneratorTree(GeneratorTree generatorTree) {
        generatorTree.addTreeSelectionListener(e -> {
            if (generatorTree.getSelectionPath().getPathCount() == 4) {//获取表相关的列
                GeneratorTreeModel.GeneratorTreeNode treeNode = (GeneratorTreeModel.GeneratorTreeNode) generatorTree.getSelectionPath().getLastPathComponent();
                Map<String,String> map = new HashMap<>();
                String dataSourceName = treeNode.getParent().getParent().getName();
                String tableType = treeNode.getParent().getName();
                String tableName = treeNode.getName();
                addTablePanel(dataSourceName,tableName);
            }
        });
        this.generatorTree = generatorTree;
    }
}
