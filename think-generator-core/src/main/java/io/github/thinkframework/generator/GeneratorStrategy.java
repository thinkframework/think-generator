package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;

/**
 * 生成器对象
 *
 * @author lixiaobin
 * @since 1.0.0
 */
public interface GeneratorStrategy<S,T> {

    void generate() throws GeneratorRuntimeException;

    GeneratorStrategy generatorConfiguration(GeneratorConfiguration generatorConfiguration);

    GeneratorStrategy dataSource(S dataSource);

    GeneratorStrategy tableName(T tableName);

}
