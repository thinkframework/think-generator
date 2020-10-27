package io.github.thinkframework.generator.core.design.strategy.impl;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.design.prototype.GeneratorPrototype;
import io.github.thinkframework.generator.core.design.strategy.AbstractStrategy;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

/**
 * 根据类生成
 *
 * @author hdhxby
 * @since 1.0.0
 */
public class GeneratorClass extends AbstractStrategy<Class,String> {
    private static final Logger log = LoggerFactory.getLogger(GeneratorClass.class);

    @Override
    public GeneratorContext<Class,String> process(GeneratorContext<Class,String> generatorContext) throws GeneratorRuntimeException {
        Stream.of(generatorContext.getSource())
            .map(clazz ->
                GeneratorPrototype.clone(generatorContext)
                    .source(clazz)
                    .target(generatorContext.getTarget()));//设置环境上下文
        return generatorContext;
    }

    @Override
    public Class<Class> source() {
        return Class.class;
    }
}
