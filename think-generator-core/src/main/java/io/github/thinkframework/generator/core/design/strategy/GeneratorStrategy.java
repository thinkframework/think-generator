package io.github.thinkframework.generator.core.design.strategy;

import io.github.thinkframework.generator.core.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.design.chainofresponsibility.GeneratorResponsibility;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

import java.util.List;

/**
 * 生成器对象
 *
 * @author lixiaobin
 * @since 1.0.0
 */
public interface GeneratorStrategy<S,T> {

    void generate(GeneratorContext<S,T> generatorContext) throws GeneratorRuntimeException;

    GeneratorStrategy responsibilitys(List<GeneratorResponsibility> responsibilitys);

    GeneratorStrategy generatorConfiguration(GeneratorConfiguration generatorConfiguration);

}
