package io.github.thinkframework.generator.core;

import io.github.thinkframework.generator.core.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.core.design.strategy.GeneratorStrategy;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

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
