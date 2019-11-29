package io.github.thinkframework.generator.provider;

import io.github.thinkframework.generator.context.GeneratorProperties;

/**
 *
 * @author lixiaobin
 * @since 2017/5/16.
 */
public interface GeneratorProvider {
    GeneratorProperties build(GeneratorProperties generatorProperties);
}
