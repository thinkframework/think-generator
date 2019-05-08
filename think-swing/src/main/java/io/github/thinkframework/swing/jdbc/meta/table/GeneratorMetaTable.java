package io.github.thinkframework.swing.jdbc.meta.table;

import io.github.thinkframework.swing.exception.GeneratorFrameRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;
import javax.swing.*;

/**
 * 内部使用的,基于MetaTableModel的JTable
 *
 * @author lixiaobin
 */
public class GeneratorMetaTable extends JTable implements ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private GeneratorMetaTable() {
    }

    public GeneratorMetaTable(DataSource dataSource, String tableName) throws GeneratorFrameRuntimeException {
        super(new MetaTableModel(dataSource, tableName));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
