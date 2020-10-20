package io.github.thinkframework.generator.design.strategy;

import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.design.chainofresponsibility.GeneratorResponsibility;
import io.github.thinkframework.generator.exception.GeneratorRuntimeException;

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
