package org.think.swing.jdbc.meta;

import org.think.swing.exception.GeneratorFrameRuntimeException;

import javax.sql.DataSource;
import javax.swing.*;

/**
 * 内部使用的,基于MetaTableModel的JTable
 *
 * @author lixiaobin
 */
public class GeneratorMetaTable extends JTable {
    private GeneratorMetaTable() {
    }

    public GeneratorMetaTable(DataSource dataSource, String tableName) throws GeneratorFrameRuntimeException {
        super(new MetaTableModel(dataSource, tableName));
    }
}
