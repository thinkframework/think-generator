package io.github.thinkframework.generator.core;

import io.github.thinkframework.generator.core.design.templatemethod.AbstractGenerator;

/**
 * 生成器对象
 *
 * @author hdhxby
 * @since 1.0.0
 */
public class GeneratorImpl<S,T> extends AbstractGenerator<S,T> implements Generator<S,T>{

    @Override
    public Generator initialize() {
        generatorStrategy.initialize();
        return this;
    }
}
