package io.github.thinkframework.generator.context;

import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.config.GeneratorProperties;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * ThreadLocal Mediator（中介者）.
 * 线程绑定
 */
public class GeneratorContext {

    private GeneratorProperties generatorProperties;

    private GeneratorConfiguration generatorConfiguration;

    private DataSource dataSource;

    private String tableName;

    private static ThreadLocal<GeneratorContext> context = ThreadLocal.withInitial(() -> {
        GeneratorContext generatorContext = new GeneratorContext();
        return generatorContext;
    });

    /**
     * 获取线程绑定的GeneratorContext
     *
     * @return GeneratorContext
     */
    public static GeneratorContext get(GeneratorConfiguration generatorConfiguration) {
        return context.get().generatorConfiguration(generatorConfiguration);
    }

    /**
     * 获取线程绑定的GeneratorContext
     *
     * @return GeneratorContext
     */
    public static GeneratorContext get() {
        return context.get();
    }


    public GeneratorConfiguration getGeneratorConfiguration() {
        return generatorConfiguration;
    }

    public GeneratorContext generatorConfiguration(GeneratorConfiguration generatorConfiguration) throws GeneratorRuntimeException {
        this.generatorConfiguration = generatorConfiguration;
        try {
            generatorProperties = new GeneratorProperties(generatorConfiguration).clone();
        } catch (CloneNotSupportedException e) {
            throw new GeneratorRuntimeException(e);
        }
        return this;
    }

    public void setGeneratorConfiguration(GeneratorConfiguration generatorConfiguration) {
        this.generatorConfiguration = generatorConfiguration;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public GeneratorContext dataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getTableName() {
        return tableName;
    }

    public GeneratorContext tableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public GeneratorProperties getGeneratorProperties() {
        return generatorProperties;
    }

    public void setGeneratorProperties(GeneratorProperties generatorProperties) {
        this.generatorProperties = generatorProperties;
    }

    public Properties getProperties() {
        return generatorProperties.getProperties();
    }
}
