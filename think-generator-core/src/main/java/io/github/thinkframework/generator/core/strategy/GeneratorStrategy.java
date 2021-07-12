package io.github.thinkframework.generator.core.strategy;

import io.github.thinkframework.generator.core.chainofresponsibility.GeneratorResponsibility;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;

import java.util.List;

/**
 * 生成器对象
 *
 * @author hdhxby
 * @version 1.0.0
* @since 1.0.0
 */
public interface GeneratorStrategy<S,T> {

    GeneratorStrategy responsibilitys(List<GeneratorResponsibility> responsibilitys);

    GeneratorStrategy addResponsibility(GeneratorResponsibility responsibility);

    Boolean execute(GeneratorConfiguration generatorConfiguration,S source,T traget);

    Class<S> source();
}
