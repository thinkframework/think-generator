package io.github.thinkframework.generator.core.design.templatemethod;

import io.github.thinkframework.generator.core.Generator;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.design.state.ErrorState;
import io.github.thinkframework.generator.core.design.strategy.GeneratorStrategy;
import io.github.thinkframework.generator.core.design.visitor.GeneratorFilesVisitor;
import io.github.thinkframework.generator.core.design.visitor.GeneratorFileVistor;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * 生成器对象
 *
 * @author hdhxby
 * @since 1.0.0
 */
public abstract class AbstractGenerator<S,T> implements Generator<S,T> {

    protected GeneratorConfiguration configuration;

    protected GeneratorStrategy generatorStrategy;

    /**
     * 生成一个运行上下文
     * @return
     */
    protected GeneratorContext generatorContext(S source,T target){
        return new GeneratorContext().generatorConfiguration(configuration).source(source).target(target);
    }

    /**
     * 运行
     * @throws GeneratorRuntimeException
     */
    @Override
    public ErrorState generate(S source,T target) throws GeneratorRuntimeException{
        ErrorState errorState = new ErrorState();
        if(Stream.of(generatorStrategy)
            .noneMatch(generatorStrategy -> generatorStrategy.source().isAssignableFrom(source.getClass()))){
            return errorState.exception(new GeneratorRuntimeException("no strategy."));
        }
        try {
            Stream.of(generatorStrategy)
                .filter(generatorStrategy -> generatorStrategy.source().isAssignableFrom(source.getClass()))
                .map(generatorStrategy -> generatorStrategy.process(generatorContext(source, target)))
                .filter(generatorContext -> errorState.getError())
                .forEach(generatorContext ->  process(generatorContext));
        } catch (GeneratorRuntimeException e){
            return errorState.exception(new GeneratorRuntimeException(e));
        } finally {
            return errorState;
        }
    }

    @Override
    public Generator generatorStrategy(GeneratorStrategy generatorStrategy) {
        this.generatorStrategy = generatorStrategy;
        return this;
    }

    @Override
    public Generator generatorConfiguration(GeneratorConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    /**
     * 输出
     * @param context
     */
    protected void process(GeneratorContext<S,T> context){
        //调用输出
        try{
            new GeneratorFilesVisitor()
                .generatorConfiguration(configuration)
                .visitor(new GeneratorFileVistor()
                    .generatorConfiguration(configuration)
                    .generatorContext(context));
        } catch (IOException e) {
            throw new GeneratorRuntimeException(e);
        }
    }
}
