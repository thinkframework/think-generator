package io.github.thinkframework.generator.beans.factory;

import io.github.thinkframework.generator.Generator;
import io.github.thinkframework.generator.core.GeneratorImpl;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.strategy.GeneratorStragetyFactory;
import io.github.thinkframework.generator.core.strategy.GeneratorStrategy;
import org.springframework.beans.factory.FactoryBean;

import java.util.Arrays;
import java.util.List;

/**
 * 工厂模式
 */
public class GeneratorFactoryBean implements FactoryBean<Generator>{

    private List<GeneratorStrategy> strategies = Arrays.asList(GeneratorStragetyFactory.getInstance().generatorTable());

    private GeneratorConfiguration configuration;

    public GeneratorFactoryBean(GeneratorConfiguration configuration) {
        this.configuration = configuration;
    }

    public void setConfiguration(GeneratorConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Generator getObject() {
        return new GeneratorImpl(configuration).strategies(strategies);
    }

    @Override
    public Class<Generator> getObjectType() {
        return Generator.class;
    }

}
