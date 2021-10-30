package io.github.thinkframework.generator.core.chain;

import io.github.thinkframework.generator.core.context.GeneratorContext;

import java.util.Iterator;
import java.util.function.Function;

/**
 * 责任链
 * Function类做的更好
 * @author hdhxby
 * @since 2017/5/16.
 */
public interface GeneratorResponsibility extends Function<GeneratorContext,GeneratorContext> {

}
