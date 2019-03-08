package io.github.thinkframework.swing.jdbc.meta;

import io.github.thinkframework.swing.jdbc.meta.table.GeneratorMetaTable;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorTablePanel extends JPanel {
    private GeneratorMetaTable generatorMetaTable;
    private GeneratorTablePanel(){

    }

    public GeneratorTablePanel(DataSource dataSource, String tableName) {
        super();
        setLayout(new BorderLayout());
        add(addNorthPanel(),BorderLayout.NORTH);
        generatorMetaTable = new GeneratorMetaTable(dataSource, tableName);
        add(new JScrollPane(generatorMetaTable));
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
                putValue(Action.SHORT_DESCRIPTION, "生成代码");
                putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("actions/refresh.png")));
            }
            public void actionPerformed(ActionEvent e) {
//                new setContext().generator();
            }
        }));
        toolBar.addSeparator();

        return toolBar;
    }
}
