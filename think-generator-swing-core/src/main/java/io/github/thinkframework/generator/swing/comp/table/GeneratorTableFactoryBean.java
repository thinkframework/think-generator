package io.github.thinkframework.generator.swing.comp.table;

import org.springframework.beans.factory.FactoryBean;

import javax.sql.DataSource;

public class GeneratorTableFactoryBean<T extends GeneratorTable> implements FactoryBean<T> {

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

    @Override
    public T getObject() {
        GeneratorTable generatorTable = new GeneratorTable(dataSource,tableName);
        //FIXME 为什么不自动调用?
        generatorTable.afterPropertiesSet();
        return (T) generatorTable;
    }

    @Override
    public Class<?> getObjectType() {
        return GeneratorTable.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
