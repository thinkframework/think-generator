package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.provider.GeneratorProvider;

import java.util.List;

/**
 * 生成器对象
 *
 * @author lixiaobin
 * @since 1.0.0
 */
public interface GeneratorStrategy<S,T> {

    void generate() throws GeneratorRuntimeException;

    GeneratorStrategy generatorConfiguration(GeneratorConfiguration generatorConfiguration);

    GeneratorStrategy generatorProviders(List<GeneratorProvider> generatorProviders);

    GeneratorStrategy dataSource(S dataSource);

    GeneratorStrategy tableName(T tableName);

}
