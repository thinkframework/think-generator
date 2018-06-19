package org.think.swing.jdbc.sql;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 扩充了工具条
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorSqlPanel extends JPanel{
    private GeneratorSqlTable generatorSqlTable;

    private GeneratorSqlPanel() {
    }


    public GeneratorSqlPanel(DataSource dataSource, String sql) {
        super();
        setLayout(new BorderLayout());
        add(addNorthPanel(),BorderLayout.NORTH);
        generatorSqlTable = new GeneratorSqlTable(dataSource,sql);
        add(new JScrollPane(generatorSqlTable));
    }

    /**
     * 顶部工具条
     * @return 顶部工具条
     */
    private JToolBar addNorthPanel(){
        JToolBar toolBar = new JToolBar();
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "第一页");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/refresh.png")));
            }
            public void actionPerformed(ActionEvent e) {
                generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "上一页");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/refresh.png")));
            }
            public void actionPerformed(ActionEvent e) {
                generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "下一页");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/refresh.png")));
            }
            public void actionPerformed(ActionEvent e) {
                generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "最后一页");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/refresh.png")));
            }
            public void actionPerformed(ActionEvent e) {
                generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.addSeparator();
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "刷新");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/refresh.png")));
            }
            public void actionPerformed(ActionEvent e) {
                generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.addSeparator();
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "增加");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/refresh.png")));
            }
            public void actionPerformed(ActionEvent e) {
                generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "删除");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/refresh.png")));
            }
            public void actionPerformed(ActionEvent e) {
                generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.addSeparator();
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "提交");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/refresh.png")));
            }
            public void actionPerformed(ActionEvent e) {
                generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.addSeparator();toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "回滚");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/refresh.png")));
            }
            public void actionPerformed(ActionEvent e) {
                generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.addSeparator();
        return toolBar;
    }
}
