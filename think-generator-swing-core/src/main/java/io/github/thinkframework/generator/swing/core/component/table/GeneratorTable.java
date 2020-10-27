package io.github.thinkframework.generator.swing.core.component.table;

import org.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;
import javax.swing.*;

/**
 * 内部使用的,基于MetaTableModel的JTable
 *
 * @author hdhxby
 */
public class GeneratorTable extends JTable implements InitializingBean {

    private DataSource dataSource;

    private String tableName;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public GeneratorTable(DataSource dataSource, String tableName) {
        this.dataSource = dataSource;
        this.tableName = tableName;
    }

    @Override
    public void afterPropertiesSet() {
        GeneratorTableModel generatorTableModel = new GeneratorTableModel(dataSource,tableName);
        generatorTableModel.afterPropertiesSet();
        setModel(generatorTableModel);
    }
}
