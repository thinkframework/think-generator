package org.think.swing.jdbc;

import org.think.swing.jdbc.sql.GeneratorSqlPanel;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 扩展了查询窗口
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorQueryPanel extends JPanel {
    private DataSource dataSource;
    private JPopupMenu jPopupMenu = new JPopupMenu();
    private JTextArea sqltext = new JTextArea();
    JTabbedPane jTabbedPane = new JTabbedPane();
    private JTextArea resultext = new JTextArea();

    private JSplitPane jSplitPane = new JSplitPane();

    private GeneratorQueryPanel(){

    }

    public GeneratorQueryPanel(DataSource dataSource){
        super();
        setDataSource(dataSource);
        setLayout(new BorderLayout());
        add(addNorthPanel(),BorderLayout.NORTH);
        add(addCenterPanel());
    }

    public JToolBar addNorthPanel(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        JToolBar jtoolBar = new JToolBar();
        final Icon execute = new ImageIcon(getClass().getClassLoader().getResource("actions/execute.png"));
        JButton newButton = new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
//				putValue(Action.NAME, "执行");
                putValue(Action.SHORT_DESCRIPTION, "执行");
                putValue(Action.SMALL_ICON, execute);
            }
            public void actionPerformed(ActionEvent e) {
//                jSplitPane.getBottomComponent().setVisible(true);
                String text = sqltext.getText();
                String[] sqls = text.split(";");
                for(int i=0;i<sqls.length;i++) {
                    String sql = sqls[i];
                    resultext.append("SQL>" + sql + "\n");
                    GeneratorSqlPanel genericSqlTablePanel = new GeneratorSqlPanel(dataSource,sql);
                    jTabbedPane.add("result",genericSqlTablePanel);
                    if(i==sqls.length-1){
                        jTabbedPane.setSelectedComponent(genericSqlTablePanel);
                    }
                }
            }
        });
        jtoolBar.add(newButton);
        jPanel.add(jtoolBar);
        return jtoolBar;
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
//                resultTable.execute(sql);
            }
        }));
        return jPopupMenu;
    }


    private DataSource getDataSource() {
        return dataSource;
    }

    private void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
