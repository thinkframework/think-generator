package io.github.thinkframework.generator.core.design.strategy;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.design.chainofresponsibility.GeneratorResponsibility;

import java.util.List;

/**
 * 生成器对象
 *
 * @author hdhxby
 * @since 1.0.0
 */
public interface GeneratorStrategy<S,T> {

    void initialize();

    GeneratorStrategy responsibilitys(List<GeneratorResponsibility> responsibilitys);

    GeneratorStrategy generatorConfiguration(GeneratorConfiguration generatorConfiguration);

    GeneratorContext<S,T> process(GeneratorContext<S,T> generatorContext);

    Class<S> source();
}
