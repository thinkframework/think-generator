package io.github.thinkframework.generator.core;

import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

/**
 * 生成器对象
 *
 * @author hdhxby
 * @version 1.0.0
* @since 1.0.0
 */
public interface Generator<S,T> {

    /**
     * 生成文件
     * @param source
     * @param target
     * @return
     * @throws GeneratorRuntimeException
     */
    void generate(S source, T target) throws GeneratorRuntimeException;

    /**
     * 清理文件
     */
    void clear() throws GeneratorRuntimeException;
}
