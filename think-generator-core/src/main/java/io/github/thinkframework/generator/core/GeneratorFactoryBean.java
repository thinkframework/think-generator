package io.github.thinkframework.generator.core;

import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import io.github.thinkframework.generator.core.design.strategy.GeneratorStrategy;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import org.springframework.beans.factory.FactoryBean;

import java.util.stream.Collectors;

/**
 * 工厂模式
 */
public class GeneratorFactoryBean implements FactoryBean<Generator> {
    private GeneratorProperties properties;

    public GeneratorProperties getProperties() {
        return properties;
    }

    public void setProperties(GeneratorProperties properties) {
        this.properties = properties;
    }

    @Override
    public Generator getObject() throws Exception {
        return new GeneratorImpl()
            .generatorStrategy(((GeneratorStrategy) Class.forName(properties.getStragegy().getClazz()).newInstance())
                .responsibilitys(properties.getStragegy().getResponsibilitys().stream().map(responsibility -> {
                    try {
                        return Class.forName(responsibility).newInstance();
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                        throw new GeneratorRuntimeException(e);
                    }
                }).collect(Collectors.toList())))
            .generatorConfiguration(properties.getConfiguration());
    }

    @Override
    public Class<Generator> getObjectType() {
        return Generator.class;
    }
}
