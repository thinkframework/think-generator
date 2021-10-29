package io.github.thinkframework.generator;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

import java.util.function.Consumer;
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
     * 生成文件
     * @param source
     * @param target
     * @return
     * @throws GeneratorRuntimeException
     */
    void generate(S source, T target) throws GeneratorRuntimeException;

    /**
     * 生成文件
     * @param supplier
     * @return
     * @throws GeneratorRuntimeException
     */
    R generate(Supplier<R> supplier) throws GeneratorRuntimeException;

    /**
     * 生成文件
     * @param supplier
     * @return
     * @throws GeneratorRuntimeException
     */
    R generate(Supplier<R> supplier, Consumer<GeneratorContext<R>> consumer) throws GeneratorRuntimeException;
}
