package io.github.thinkframework.generator.context;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * ThreadLocal Mediator（中介者）.
 * 线程绑定
 */
public class GeneratorContext {
    private GeneratorProperties generatorProperties;
    private DataSource dataSource;
    static ThreadLocal<GeneratorContext> context = new ThreadLocal<>();

    /**
     * 获取线程绑定的GeneratorContext
     *
     * @return GeneratorContext
     */
    public static GeneratorContext getContext() {
        GeneratorContext generatorContext = context.get();
        if (generatorContext == null) {
            generatorContext = new GeneratorContext();
            setContext(generatorContext);
        }
        return context.get();
    }

    public static void setContext(GeneratorContext generatorContext) {
        context.set(generatorContext);
    }

    public GeneratorContext() {

    }

    public GeneratorContext(DataSource dataSource, String tableName) {
        this(dataSource, new Properties(), null,tableName);
    }

    public GeneratorContext(String configLocation, DataSource dataSource, String tableName) {
        this(dataSource, new Properties(), configLocation,tableName);
    }

    public GeneratorContext(GeneratorProperties generatorProperties) {
        this.generatorProperties = generatorProperties;
    }

    public GeneratorContext(DataSource dataSource, Properties properties, String configLocation, String tableName) {
        if (dataSource != null) {
            this.dataSource = dataSource;
        }
        if (properties == null) {
            properties = new Properties();
        }
        properties.setProperty("configLocation", configLocation);
        properties.setProperty("tableName", tableName);
        generatorProperties = new GeneratorProperties(properties);
    }

    public void setProperties(Properties properties) {
        generatorProperties = new GeneratorProperties();
        generatorProperties.setProperties(properties);
    }

    public GeneratorProperties getGeneratorProperties() {
        return generatorProperties;
    }

    public GeneratorContext setGeneratorProperties(GeneratorProperties generatorProperties) {
        this.generatorProperties = generatorProperties;
        return this;
    }

    public Properties getProperties() {
        return getGeneratorProperties().getProperties();
    }

    public Object getProperty(String key) {
        return getProperties().getProperty(key);
    }

    public Object getProperty(String key, String defaultValue) {
        return getProperties().getProperty(key, defaultValue);
    }

    public GeneratorContext setProperty(String key, String value) {
        getProperties().setProperty(key, value);
        return this;
    }

    public String getConfigLocation() {
        return generatorProperties.getProperty("configLocation");
    }

    public GeneratorContext setConfigLocation(String configLocation) {
        generatorProperties.setProperty("configLocation", configLocation);
        return this;
    }

    public String getTableName() {
        return generatorProperties.getProperty("tableName");
    }

    public void setTableName(String tableName) {
        generatorProperties.setProperty("tableName", tableName);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public GeneratorContext setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }
}
