package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorProperties;
import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.provider.GeneratorProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;

import java.util.stream.Collectors;

/**
 * 工厂模式
 */
@Slf4j
public class GeneratorFactoryBean implements BeanFactoryAware, BeanNameAware, FactoryBean<Generator> {

    public static final String DEFAULT_GENERATOR = "io.github.thinkframework.generator.GeneratorTable";

    private BeanFactory beanFactory;

    private String name;

    private String clazz = DEFAULT_GENERATOR;

    private Generator generator;

    @Override
    public Generator getObject() throws Exception {
        generator = ((Generator)Class.forName(clazz).newInstance())
            .generatorConfiguration(beanFactory.getBean(GeneratorProperties.class).getConfiguration())
            .generatorProviders(beanFactory.getBeanProvider(GeneratorProvider.class)
            .orderedStream().collect(Collectors.toList()));
        return generator;
    }

    @Override
    public Class<Generator> getObjectType() {
        return Generator.class;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
