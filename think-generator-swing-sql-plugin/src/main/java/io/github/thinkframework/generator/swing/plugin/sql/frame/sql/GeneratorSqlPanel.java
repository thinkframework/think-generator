package io.github.thinkframework.generator.swing.plugin.sql.frame.sql;

import io.github.thinkframework.generator.swing.plugin.sql.component.sql.GeneratorSql;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 扩展了查询窗口
 * @author hdhxby
 * @since 2017/3/24
 */
public class GeneratorSqlPanel extends JPanel {
    private DataSource dataSource;
    private JPopupMenu jPopupMenu = new JPopupMenu();
    private JTextArea sqltext = new JTextArea();
    private JTabbedPane jTabbedPane = new JTabbedPane();
    private JTextArea resultext = new JTextArea();

    private JSplitPane jSplitPane = new JSplitPane();

    public JToolBar addToolBar(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        JToolBar toolBar = new JToolBar();
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
				putValue(Action.NAME, ">");
                putValue(Action.SHORT_DESCRIPTION, "Execute");
            }
            public void actionPerformed(ActionEvent e) {
                String text = sqltext.getText();
                String[] sqls = text.split(";");
                for(int i=0;i<sqls.length;i++) {
                    String sql = sqls[i];
                    if(i==sqls.length-1){
                        EventQueue.invokeLater(() -> {
                            GeneratorSql generatorSql = new GeneratorSql();
                            generatorSql.setDataSource(dataSource);
                            generatorSql.setSql(sql);
                            GeneratorSqlTablePanel generatorSqlTablePanel = new GeneratorSqlTablePanel();
                            generatorSqlTablePanel.setGeneratorSql(generatorSql);
                            jTabbedPane.add("result"+ jTabbedPane.getTabCount(), generatorSqlTablePanel);
                            jTabbedPane.setSelectedComponent(generatorSqlTablePanel);
                            generatorSqlTablePanel.afterPropertiesSet();
                        });

                    }
                }
            }
        }));
        toolBar.addSeparator();
        JComboBox tx = new JComboBox(new String[]{"Auto","Manual"});
        tx.addItemListener(e -> e.getItem());
        toolBar.add(tx);
//        toolBar.add(new JButton( new AbstractAction() {
//            private static final long serialVersionUID = 1L;
//            {
//                putValue(Action.SHORT_DESCRIPTION, "Submit");
////                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/commit.png")));
//            }
//            public void actionPerformed(ActionEvent e) {
//                //generatorSqlTable.acceptChanges();
//            }
//        }));
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "-");
                putValue(Action.SHORT_DESCRIPTION, "Commit");
//                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/commit.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "x");
                putValue(Action.SHORT_DESCRIPTION, "Rollback");
//                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/rollback.png")));
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

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
