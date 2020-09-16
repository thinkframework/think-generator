package io.github.thinkframework.generator.design.chain.of.responsibility;

import io.github.thinkframework.generator.context.GeneratorContext;

/**
 * @author lixiaobin
 * @since 2017/5/16.
 */
public interface GeneratorResponsibility {
    GeneratorContext process(GeneratorContext generatorContext);
}
