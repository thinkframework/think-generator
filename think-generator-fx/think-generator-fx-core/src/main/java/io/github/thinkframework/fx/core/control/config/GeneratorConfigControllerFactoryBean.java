package io.github.thinkframework.fx.core.control.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;

@Slf4j
public class GeneratorConfigControllerFactoryBean<T extends GeneratorConfigController> implements FactoryBean<T>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private DataSource dataSource;

    private String datasourceName;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }

    @Override
    public T getObject() throws Exception {
        GeneratorConfigController generatorTable = new GeneratorConfigController();
//        generatorTable.setDataSource(dataSource);
//        generatorTable.setDataSourceName(datasourceName);
//        //FIXME 为什么不自动调用?
//        generatorTable.afterPropertiesSet();
        return (T) generatorTable;
    }

    @Override
    public Class<?> getObjectType() {
        return GeneratorConfigController.class;
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

