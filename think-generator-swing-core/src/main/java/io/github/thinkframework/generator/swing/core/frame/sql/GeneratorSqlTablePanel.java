package io.github.thinkframework.generator.swing.core.frame.sql;

import io.github.thinkframework.generator.swing.core.component.sql.GeneratorSql;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 扩充了工具条
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorSqlTablePanel extends JPanel {

    private GeneratorSql generatorSql;

    private DataSource dataSource;
    private String sql;

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
                putValue(Action.SHORT_DESCRIPTION, "第一页");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/allLeft.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "上一页");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/left.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.add(new TextField("rows"));
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "下一页");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/right.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "最后一页");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/allRight.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
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
                //generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.addSeparator();
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "增加");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("general/add.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
            }
        }));
        toolBar.add(new JButton( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.SHORT_DESCRIPTION, "删除");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("general/remove.png")));
            }
            public void actionPerformed(ActionEvent e) {
                //generatorSqlTable.acceptChanges();
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

        toolBar.addSeparator();
        return toolBar;
    }

    public void afterPropertiesSet() {
        setLayout(new BorderLayout());
        add(addToolbar(),BorderLayout.NORTH);

        generatorSql.setDataSource(dataSource);
        generatorSql.setSql(sql);
        generatorSql.afterPropertiesSet();
        add(new JScrollPane(generatorSql));
    }

    public GeneratorSql getGeneratorSqlTable() {
        return generatorSql;
    }

    public void setGeneratorSqlTable(GeneratorSql generatorSql) {
        this.generatorSql = generatorSql;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
