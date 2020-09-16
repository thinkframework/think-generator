package io.github.thinkframework.generator.context;

import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;

import java.util.Properties;

/**
 * ThreadLocal Mediator（中介者）.
 * 线程绑定
 */
public class GeneratorContext<S,T> {

    private Properties properties = new Properties();

    private GeneratorConfiguration generatorConfiguration;

    private S source;

    private T target;

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
        return this;
    }

    public void setGeneratorConfiguration(GeneratorConfiguration generatorConfiguration) {
        this.generatorConfiguration = generatorConfiguration;
    }

    public S getSource() {
        return source;
    }

    public GeneratorContext source(S source) {
        this.source = source;
        return this;
    }

    public void setSource(S source) {
        this.source = source;
    }

    public T getTarget() {
        return target;
    }

    public GeneratorContext target(T target) {
        this.target = target;
        return this;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    public Properties getProperties() {
        return properties;
    }
}
