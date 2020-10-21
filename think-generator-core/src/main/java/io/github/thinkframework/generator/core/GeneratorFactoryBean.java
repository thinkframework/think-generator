package io.github.thinkframework.generator.core;

import io.github.thinkframework.generator.core.config.GeneratorProperties;
import io.github.thinkframework.generator.core.design.strategy.GeneratorStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;

import java.util.stream.Collectors;

/**
 * 工厂模式
 */
@Slf4j
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
        GeneratorStrategy generatorStrategy = (GeneratorStrategy)Class.forName(properties.getStragegy().getClazz()).newInstance();
        generatorStrategy.responsibilitys(properties.getStragegy().getResponsibilitys().stream().map(provider ->{
            //TODO
            try {
                return Class.forName(provider).newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList()));
        return new GeneratorImpl()
            .generatorStrategy(generatorStrategy)
            .generatorConfiguration(properties.getConfiguration());
    }

    @Override
    public Class<Generator> getObjectType() {
        return Generator.class;
    }
}
