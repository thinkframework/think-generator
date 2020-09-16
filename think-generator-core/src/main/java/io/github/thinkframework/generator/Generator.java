package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.design.strategy.GeneratorStrategy;

/**
 * 生成器对象
 *
 * @author lixiaobin
 * @since 1.0.0
 */
public interface Generator<S,T> {

    Generator source(S source);

    Generator target(T target);

    void generate() throws GeneratorRuntimeException;

    Generator generatorStrategy(GeneratorStrategy generatorStrategy);

    Generator generatorConfiguration(GeneratorConfiguration generatorConfiguration);

}
