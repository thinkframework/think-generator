package io.github.thinkframework.generator.core.design.strategy;

import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.design.chainofresponsibility.ConfigurationGeneratorResponsibility;
import io.github.thinkframework.generator.core.design.chainofresponsibility.GeneratorResponsibility;

import java.util.*;

/**
 * 根据Class生成
 *
 * @author hdhxby
 * @since 1.0.0
 */
public abstract class AbstractStrategy<S,T> implements GeneratorStrategy<S,T> {

    protected List<GeneratorResponsibility> responsibilitys = new ArrayList<>();

    protected GeneratorConfiguration configuration;

    @Override
    public void initialize() {
        responsibilitys.add(0,new ConfigurationGeneratorResponsibility());
    }

    @Override
    public GeneratorStrategy responsibilitys(List<GeneratorResponsibility> responsibilitys){
        this.responsibilitys = responsibilitys;
        return this;
    }

    @Override
    public GeneratorStrategy generatorConfiguration(GeneratorConfiguration configuration){
        this.configuration = configuration;
        return this;
    }

    /**
     * 输出
     * 可以抽到责任链,不值当的
     * @param generatorContext
     */
    @Override
    public abstract GeneratorContext<S,T> process(GeneratorContext<S,T> generatorContext);

}
