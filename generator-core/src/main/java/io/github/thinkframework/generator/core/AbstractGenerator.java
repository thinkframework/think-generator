package io.github.thinkframework.generator.core;

import io.github.thinkframework.generator.Generator;
import io.github.thinkframework.generator.core.chain.GeneratorResponsibility;
import io.github.thinkframework.generator.core.command.GeneratorCommand;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
public abstract class AbstractGenerator<T, U, R> implements Generator<T, U, R> {

    protected GeneratorConfiguration configuration;

    protected GeneratorResponsibility responsibility;

    protected GeneratorCommand command;


    public AbstractGenerator(GeneratorConfiguration configuration) {
        this.configuration = configuration;
    }

    public GeneratorConfiguration getConfiguration() {
        return configuration;
    }

    public Generator configuration(GeneratorConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    public void setConfiguration(GeneratorConfiguration configuration) {
        this.configuration = configuration;
    }

    public GeneratorResponsibility getResponsibility() {
        return responsibility;
    }

    public Generator responsibility(GeneratorResponsibility responsibility) {
        this.responsibility = responsibility;
        return this;
    }

    public void setResponsibility(GeneratorResponsibility responsibility) {
        this.responsibility = responsibility;
    }

    public GeneratorCommand getCommand() {
        return command;
    }

    public Generator command(GeneratorCommand command) {
        this.command = command;
        return this;
    }

    public void setCommand(GeneratorCommand command) {
        this.command = command;
    }

    /**
     * 运行
     *
     * @throws GeneratorRuntimeException
     */
    @Override
    public void generate(Supplier<GeneratorContext> supplier, Function<GeneratorContext,GeneratorContext> responsibility) throws GeneratorRuntimeException {
        responsibility.apply(Optional.of(supplier.get()) // 1.获取到第一个元素
                .map(source -> new GeneratorContext()
                        .configuration(configuration.clone())
                        .source(source)) // 2.转换成GeneratorContext
                .get()); // 3.加工
    }

    /**
     * 运行
     * @throws GeneratorRuntimeException
     */
    public R generate(Supplier<GeneratorContext> supplier, Function<GeneratorContext,GeneratorContext> responsibility, Function<GeneratorContext,R> command) throws GeneratorRuntimeException{
        return command.apply( // 4.最终执行
                responsibility.apply(Optional.of(supplier.get()) // 1.获取到第一个元素
                        .map(source -> new GeneratorContext()
                                .configuration(configuration.clone())
                                .source(source)) // 2.转换成GeneratorContext
                        .get()) // 3.加工
        );
    }

    /**
     * 运行
     *
     * @throws GeneratorRuntimeException
     */
    @Override
    public void generate(Supplier<GeneratorContext> supplier, Function<GeneratorContext,GeneratorContext> responsibility, Consumer<GeneratorContext> command) throws GeneratorRuntimeException {
        command.accept( // 4.最终执行
            responsibility.apply(Optional.of(supplier.get()) // 1.获取到第一个元素
                        .map(source -> new GeneratorContext()
                            .configuration(configuration.clone())
                            .source(source)) // 2.转换成GeneratorContext
                        .get()) // 3.加工
        );
    }
}
