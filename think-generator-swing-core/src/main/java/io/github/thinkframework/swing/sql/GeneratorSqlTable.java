package io.github.thinkframework.swing.sql;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import javax.swing.*;

/**
 * 内部使用的,基于SqlTableModel的可编辑的JTable
 * @see GeneratorSqlTableModel
 * @author lixiaobin
 */
@Slf4j
public class GeneratorSqlTable extends JTable {

    GeneratorSqlTableModel generatorSqlTableModel;

    private DataSource dataSource;
    private String sql;

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

    public void afterPropertiesSet() {
        generatorSqlTableModel.setDataSource(dataSource);
        generatorSqlTableModel.setSql(sql);
        generatorSqlTableModel.afterPropertiesSet();
        setModel(generatorSqlTableModel);
    }
}
