package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.design.strategy.GeneratorStrategy;

/**
 * 生成器对象
 *
 * @author lixiaobin
 * @since 1.0.0
 */
public class GeneratorImpl<S,T> implements Generator<S,T>{

    private S soure;

    private T target;

    private GeneratorConfiguration configuration;

    private GeneratorStrategy generatorStrategy;

    @Override
    public Generator source(S source) {
        this.soure = source;
        return this;
    }

    @Override
    public Generator target(T target) {
        this.target = target;
        return this;
    }

    public GeneratorContext generatorContext(){
        return new GeneratorContext()
            .generatorConfiguration(configuration)
            .source(soure)
            .target(target);
    }

    @Override
    public void generate() throws GeneratorRuntimeException{
        generatorStrategy.generate(generatorContext());
    }

    @Override
    public Generator generatorStrategy(GeneratorStrategy generatorStrategy) {
        this.generatorStrategy = generatorStrategy;
        return this;
    }

    @Override
    public Generator generatorConfiguration(GeneratorConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }
}
