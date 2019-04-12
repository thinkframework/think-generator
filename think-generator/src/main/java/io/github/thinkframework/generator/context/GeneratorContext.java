package io.github.thinkframework.generator.context;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * ThreadLocal Mediator（中介者）.
 * 线程绑定
 */
public class GeneratorContext {
    private GeneratorProperties generatorProperties;

    private static ThreadLocal<GeneratorContext> context = new ThreadLocal<>();

    /**
     * 获取线程绑定的GeneratorContext
     *
     * @return GeneratorContext
     */
    public static GeneratorContext get() {
        GeneratorContext generatorContext = context.get();
        if (generatorContext == null) {
            context.set(new GeneratorContext());
        }
        return context.get();
    }


    public static GeneratorContext set(GeneratorProperties generatorProperties) {
        GeneratorContext generatorContext = context.get();
        if (generatorContext == null) {
            context.set(new GeneratorContext(generatorProperties));
        }
        return context.get();
    }

    public GeneratorContext() {

    }

    public GeneratorContext(GeneratorProperties generatorProperties) {
        this.generatorProperties = generatorProperties;
    }


//    public void setProperties(Properties properties) {
//        generatorProperties = new GeneratorProperties();
//        generatorProperties.setProperties(properties);
//    }

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

    public String getTableName() {
        return generatorProperties.getTableName();
    }

    public DataSource getDataSource() {
        return generatorProperties.getDataSource();
    }

}
