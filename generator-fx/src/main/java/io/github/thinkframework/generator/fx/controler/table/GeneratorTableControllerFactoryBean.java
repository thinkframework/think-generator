package io.github.thinkframework.generator.fx.controler.table;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;

public class GeneratorTableControllerFactoryBean<T extends GeneratorTableController> implements FactoryBean<T>, ApplicationContextAware {

    private ApplicationContext applicationContext;

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
    public T getObject() throws Exception {
        GeneratorTableController generatorTable = new GeneratorTableController();
        generatorTable.setDataSource(dataSource);
        generatorTable.setTableName(tableName);
        //FIXME 为什么不自动调用?
        generatorTable.afterPropertiesSet();
        return (T) generatorTable;
    }

    @Override
    public Class<?> getObjectType() {
        return GeneratorTableController.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

