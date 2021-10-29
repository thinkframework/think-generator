package io.github.thinkframework.generator.core.context;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

import java.util.Properties;

/**
 * ThreadLocal.
 * 线程绑定
 */
public class GeneratorContext<T>{

    private Properties properties = new Properties();

    private GeneratorConfiguration generatorConfiguration;

    private T source;

    private static ThreadLocal<GeneratorContext> context = ThreadLocal.withInitial(() -> {
        GeneratorContext generatorContext = new GeneratorContext();
        return generatorContext;
    });

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
        return this;
    }

    public void setGeneratorConfiguration(GeneratorConfiguration generatorConfiguration) {
        this.generatorConfiguration = generatorConfiguration;
    }

    public Properties getProperties() {
        return properties;
    }

    public T getSource() {
        return source;
    }

    public GeneratorContext<T> source(T source) {
        this.source = source;
        return this;
    }

    public void setSource(T source) {
        this.source = source;
    }

}
