package io.github.thinkframework.generator.swing.core.frame.sql;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 扩展了查询窗口
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorSqlPanel extends JPanel {
    private DataSource dataSource;
    private JPopupMenu jPopupMenu = new JPopupMenu();
    private JTextArea sqltext = new JTextArea();
    private JTabbedPane jTabbedPane = new JTabbedPane();
    private JTextArea resultext = new JTextArea();

    private JSplitPane jSplitPane = new JSplitPane();

    private GeneratorSqlTablePanel generatorSqlTablePanel;

    public JToolBar addToolBar(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        JToolBar toolBar = new JToolBar();
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
//				putValue(Action.NAME, "执行");
                putValue(Action.SHORT_DESCRIPTION, "执行");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/execute.png")));
            }
            public void actionPerformed(ActionEvent e) {
//                jSplitPane.getBottomComponent().setVisible(true);
                String text = sqltext.getText();
                String[] sqls = text.split(";");
                for(int i=0;i<sqls.length;i++) {
                    String sql = sqls[i];
                    resultext.append("SQL>" + sql + "\n");
                    generatorSqlTablePanel.setDataSource(dataSource);
                    generatorSqlTablePanel.setSql(sql);
                    generatorSqlTablePanel.afterPropertiesSet();
                    jTabbedPane.add("result", generatorSqlTablePanel);
                    if(i==sqls.length-1){
                        jTabbedPane.setSelectedComponent(generatorSqlTablePanel);
                    }
                }
            }
        }));
        toolBar.addSeparator();
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "提交");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/commit.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "回滚");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/rollback.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.addSeparator();
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "取消");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/suspend.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
            }
        }));

        jPanel.add(toolBar);
        return toolBar;
    }

    public JSplitPane addCenterPanel(){
        jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPane.setOneTouchExpandable(true);
        jSplitPane.setContinuousLayout(true);
        jSplitPane.setTopComponent(addCenterNorthPanel());
        jSplitPane.setBottomComponent(addCenterCenterPanel());
        return jSplitPane;
    }

    public JScrollPane addCenterNorthPanel(){
        sqltext.setComponentPopupMenu(addJPopuMenu());
        JScrollPane scrollPane = new JScrollPane(sqltext);
        sqltext.setLineWrap(true);
        sqltext.setRows(15);
        return scrollPane;
    }


    public JTabbedPane addCenterCenterPanel(){
        resultext.setLineWrap(true);
        resultext.setRows(15);
        jTabbedPane.add("output",new JScrollPane(resultext));
//        jTabbedPane.add("result",new JScrollPane(resultTable));
        return jTabbedPane;
    }

    private JPopupMenu addJPopuMenu(){
        jPopupMenu.add(new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "执行");
            }
            public void actionPerformed(ActionEvent e) {
                String sql = sqltext.getText();
//                resultTable.execute(data);
            }
        }));
        return jPopupMenu;
    }


    public void afterPropertiesSet() {
        setLayout(new BorderLayout());
        add(addToolBar(),BorderLayout.NORTH);
        add(addCenterPanel());
    }

    public GeneratorSqlTablePanel getGeneratorSqlTablePanel() {
        return generatorSqlTablePanel;
    }

    public void setGeneratorSqlTablePanel(GeneratorSqlTablePanel generatorSqlTablePanel) {
        this.generatorSqlTablePanel = generatorSqlTablePanel;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
