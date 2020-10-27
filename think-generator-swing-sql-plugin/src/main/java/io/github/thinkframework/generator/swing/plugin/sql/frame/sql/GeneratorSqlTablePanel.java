package io.github.thinkframework.generator.swing.plugin.sql.frame.sql;

import io.github.thinkframework.generator.swing.plugin.sql.component.sql.GeneratorSql;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 扩充了工具条
 * @author hdhxby
 * @since 2017/3/24
 */
public class GeneratorSqlTablePanel extends JPanel {

    private GeneratorSql generatorSql;

    /**
     * 顶部工具条
     * @return 顶部工具条
     */
    private JToolBar addToolbar(){
        JToolBar toolBar = new JToolBar();

//        new ImageTranscoder().
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "|<");
                putValue(Action.SHORT_DESCRIPTION, "Fist page");
//                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/allLeft.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "|<");
                putValue(Action.SHORT_DESCRIPTION, "Previous Page");
//                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/left.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
            }
        }));
        JComboBox rows = new JComboBox(new Integer[]{10,20,50,100});
        rows.addItemListener(e -> e.getItem());
        toolBar.add(rows);
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, ">");
                putValue(Action.SHORT_DESCRIPTION, "Next Page");
//                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/right.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, ">|");
                putValue(Action.SHORT_DESCRIPTION, "Last Page");
//                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/allRight.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.addSeparator();
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "O");
                putValue(Action.SHORT_DESCRIPTION, "Reload Page");
//                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/refresh.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.addSeparator();
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "+");
                putValue(Action.SHORT_DESCRIPTION, "Add New Row");
//                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("general/add.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "-");
                putValue(Action.SHORT_DESCRIPTION, "Delete Rows");
//                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("general/remove.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
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
        toolBar.addSeparator();
//        toolBar.add(new JButton( new AbstractAction() {
//            private static final long serialVersionUID = 1L;
//            {
//                putValue(Action.SHORT_DESCRIPTION, "取消");
////                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/suspend.png")));
//            }
//            public void actionPerformed(ActionEvent e) {
//                //generatorSqlTable.acceptChanges();
//            }
//        }));

        toolBar.addSeparator();
        return toolBar;
    }

    public void afterPropertiesSet() {
        setLayout(new BorderLayout());
        add(addToolbar(),BorderLayout.NORTH);
        add(new JScrollPane(generatorSql));
    }

    public GeneratorSql getGeneratorSql() {
        return generatorSql;
    }

    public void setGeneratorSql(GeneratorSql generatorSql) {
        this.generatorSql = generatorSql;
    }
}
