package io.github.thinkframework.generator;

import io.github.thinkframework.generator.core.chain.GeneratorResponsibility;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 生成器对象
 *
 * @author hdhxby
 * @version 1.0.0
* @since 1.0.0
 */
public interface Generator<S,T,R> {

    /**
     * 运行
     * @throws GeneratorRuntimeException
     */
    void generate(Supplier<GeneratorContext> supplier, Function<GeneratorContext,GeneratorContext> responsibility, Consumer<GeneratorContext> command) throws GeneratorRuntimeException;
}
