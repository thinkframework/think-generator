package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;

/**
 * 工厂模式
 */
@Slf4j
public class GeneratorFactoryBean implements BeanFactoryAware, BeanNameAware, FactoryBean<GeneratorStrategy> {

    private BeanFactory beanFactory;

    private String name;

    private GeneratorProperties properties;

    private GeneratorStrategy generator;

    public void setProperties(GeneratorProperties properties) {
        this.properties = properties;
    }

    @Override
    public GeneratorStrategy getObject() throws Exception {
        generator = ((GeneratorStrategy)Class.forName(properties.getStragegy()).newInstance())
            .generatorConfiguration(properties.getConfiguration());
        return generator;
    }

    @Override
    public Class<GeneratorStrategy> getObjectType() {
        return GeneratorStrategy.class;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
