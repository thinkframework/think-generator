package io.github.thinkframework.generator.core.design.chainofresponsibility;

import io.github.thinkframework.generator.core.context.GeneratorContext;

/**
 * @author lixiaobin
 * @since 2017/5/16.
 */
public interface GeneratorResponsibility {
    GeneratorContext process(GeneratorContext generatorContext);
}
