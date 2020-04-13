package io.github.thinkframework.generator.provider;

import io.github.thinkframework.generator.context.GeneratorContext;

/**
 * @author lixiaobin
 * @since 2017/5/16.
 */
public interface GeneratorProvider {
    GeneratorContext build(GeneratorContext generatorContext);
}
