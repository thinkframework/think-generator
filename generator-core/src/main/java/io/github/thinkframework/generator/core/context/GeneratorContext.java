package io.github.thinkframework.generator.core.context;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

import java.util.Properties;
import java.util.function.Supplier;

/**
 * ThreadLocal.
 * 线程绑定
 */
public class GeneratorContext<T> implements Supplier<GeneratorContext<T>> {

    private Properties properties = new Properties();

    private GeneratorConfiguration configuration;

    private T source;

    /**
     * 获取线程绑定的GeneratorContext
     *
     * @return GeneratorContext
     */
    public GeneratorContext get() {
        return this;
    }

    public GeneratorConfiguration getConfiguration() {
        return configuration;
    }

    public GeneratorContext configuration(GeneratorConfiguration generatorConfiguration) throws GeneratorRuntimeException {
        this.configuration = generatorConfiguration;
        return this;
    }

    public void setConfiguration(GeneratorConfiguration configuration) {
        this.configuration = configuration;
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

    public static class Builder<T> {

        private GeneratorConfiguration configuration;

        private T source;


        public Builder configuration(GeneratorConfiguration generatorConfiguration) throws GeneratorRuntimeException {
            this.configuration = generatorConfiguration;
            return this;
        }

        public Builder<T> source(T source) {
            this.source = source;
            return this;
        }

        public GeneratorContext<T> build() {
            GeneratorContext<T> generatorContext = new GeneratorContext();
            generatorContext.setConfiguration(configuration);
            generatorContext.setSource(source);
            return generatorContext;
        }
    }
}
