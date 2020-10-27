package io.github.thinkframework.generator.core;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.design.state.ErrorState;
import io.github.thinkframework.generator.core.design.strategy.GeneratorStrategy;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

/**
 * 生成器对象
 *
 * @author hdhxby
 * @since 1.0.0
 */
public interface Generator<S,T> {

    Generator generatorConfiguration(GeneratorConfiguration generatorConfiguration);

    Generator generatorStrategy(GeneratorStrategy generatorStrategy);

    /**
     * 初始化
     * @return
     */
    Generator initialize();

    ErrorState generate(S source, T target) throws GeneratorRuntimeException;


}
