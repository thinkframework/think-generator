package io.github.thinkframework.generator.core;

import io.github.thinkframework.generator.Generator;
import io.github.thinkframework.generator.core.chain.GeneratorResponsibility;
import io.github.thinkframework.generator.core.command.GeneratorCommand;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 生成器对象
 *
 * @author hdhxby
 * @version 1.0.0
* @since 1.0.0
 */
public class AbstractGenerator<T, U, R> implements Generator<T, U, R> {

    protected GeneratorConfiguration configuration;


    protected List<GeneratorResponsibility> responsibilitys = new ArrayList<>();

    protected GeneratorCommand command;

    public AbstractGenerator() {

    }

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

    public Generator responsibilitys(List<GeneratorResponsibility> responsibilitys) {
        this.responsibilitys = responsibilitys;
        return this;
    }

    public Generator addResponsibility(GeneratorResponsibility responsibility) {
        this.responsibilitys.add(responsibility);
        return this;
    }


    /**
     * 生成文件
     * @param source
     * @param target
     * @return
     * @throws GeneratorRuntimeException
     */
    public void generate(T source, U target) throws GeneratorRuntimeException {
        throw new GeneratorRuntimeException("");
    }
    /**
     * 运行
     * @throws GeneratorRuntimeException
     */
    @Override
    public R generate(Supplier<R> supplier) throws GeneratorRuntimeException{
        generate(supplier,(context) -> command.run(context));
        return null;
    }


    /**
     * 运行
     * @throws GeneratorRuntimeException
     */
    @Override
    public R generate(Supplier<R> supplier, Consumer<GeneratorContext<R>> consumer) throws GeneratorRuntimeException{
        GeneratorContext<R> generatorContext = GeneratorContext.get()
                .generatorConfiguration(configuration)
                .source(supplier.get());
        // 调用责任链
        responsibilitys.stream().findFirst().get()
                .execute(responsibilitys.iterator(),generatorContext);
        // 执行最终调用
        consumer.accept(generatorContext);
        return null;
    }
}
