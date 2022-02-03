package io.github.thinkframework.swing.core.component.panel;

import org.springframework.beans.factory.FactoryBean;

import javax.sql.DataSource;

public class GeneratorPanelFactoryBean<T extends GeneratorPanel> implements FactoryBean<T> {

    private DataSource dataSource;

    private String dataSourceName;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    @Override
    public T getObject() {
        GeneratorPanel dataSourcePanel = new GeneratorPanel();
        dataSourcePanel.setDataSourceName(dataSourceName);
        dataSourcePanel.afterPropertiesSet();
        return (T) dataSourcePanel;
    }

    @Override
    public Class<?> getObjectType() {
        return GeneratorPanel.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
