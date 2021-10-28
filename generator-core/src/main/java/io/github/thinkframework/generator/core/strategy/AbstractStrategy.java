package io.github.thinkframework.generator.core.strategy;

import io.github.thinkframework.generator.core.chainofresponsibility.GeneratorResponsibility;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象父类,模板方法
 *
 * @author hdhxby
 * @version 1.0.0
* @since 1.0.0
 */
public abstract class AbstractStrategy<S,T> implements GeneratorStrategy<S,T> {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected List<GeneratorResponsibility> responsibilitys = new ArrayList<>();

    @Override
    public Boolean execute(GeneratorConfiguration generatorConfiguration, S source, T traget){
        log.info("传入的名称:{}", traget);
        execute(GeneratorContext.get()
            .generatorConfiguration(generatorConfiguration)
            ,source,traget);
        return true;
    }

    /**
     *
     * @param generatorContext 线程安全的上下文
     * @param source
     * @param traget
     */
    protected abstract void execute(GeneratorContext<S,T> generatorContext, S source, T traget);

    @Override
    public GeneratorStrategy responsibilitys(List<GeneratorResponsibility> responsibilitys){
        this.responsibilitys = responsibilitys;
        return this;
    }

    @Override
    public GeneratorStrategy addResponsibility(GeneratorResponsibility responsibility){
        responsibilitys.add(responsibility);
        return this;
    }
}
