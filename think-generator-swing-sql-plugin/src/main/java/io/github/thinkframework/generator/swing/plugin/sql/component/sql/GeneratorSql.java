package io.github.thinkframework.generator.swing.plugin.sql.component.sql;

import javax.sql.DataSource;
import javax.swing.*;

/**
 * 内部使用的,基于SqlTableModel的可编辑的JTable
 * @see GeneratorSqlModel
 * @author hdhxby
 */
public class GeneratorSql extends JTable {

    GeneratorSqlModel generatorSqlModel = new GeneratorSqlModel();

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
        generatorSqlModel.setDataSource(dataSource);
        generatorSqlModel.setSql(sql);
        generatorSqlModel.afterPropertiesSet();
        setModel(generatorSqlModel);
    }
}
