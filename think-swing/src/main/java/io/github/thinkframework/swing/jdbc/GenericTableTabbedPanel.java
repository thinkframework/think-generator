package io.github.thinkframework.swing.jdbc;

import io.github.thinkframework.swing.jdbc.sql.GeneratorSqlPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.thinkframework.swing.jdbc.meta.GeneratorTablePanel;

import javax.sql.DataSource;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author lixiaobin
 */
public class GenericTableTabbedPanel extends JTabbedPane{
    private String sql = "SELECT * FROM ";
    private Logger logger = LoggerFactory.getLogger(getClass());
    private GenericTableTabbedPanel() {
    }

    public GenericTableTabbedPanel(DataSource dataSource, String tableName) {
        addTab("列", null, new JScrollPane(new GeneratorTablePanel(dataSource,tableName)), null);
        addTab("数据", null, new JScrollPane(new GeneratorSqlPanel(dataSource,sql+tableName)), null);
        addChangeListener(e ->{
                int selectedIndex = getSelectedIndex();
                if(selectedIndex==1){
//                    sqlTable.execute("select * from "+tableName);
                }
            });
    }
}
