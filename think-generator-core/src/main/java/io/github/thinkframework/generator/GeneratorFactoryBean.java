package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import io.github.thinkframework.generator.provider.GeneratorProvider;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 工厂模式
 */
public class GeneratorFactoryBean implements BeanFactoryAware, BeanNameAware, FactoryBean, BeanFactoryPostProcessor {

    public static final String DEFAULT_GENERATOR = "io.github.thinkframework.generator.GeneratorTable";

    private BeanFactory beanFactory;

    private String name;

    private String clazz;

    private GeneratorConfiguration generatorConfiguration;

    private Generator generator;

    public GeneratorFactoryBean() {

    }

    @Override
    public Generator getObject() throws Exception {
        return generator
            .generatorConfiguration(generatorConfiguration)
            .generatorProviders(beanFactory.getBeanProvider(GeneratorProvider.class)
                .orderedStream().collect(Collectors.toList()));
    }

    @Override
    public Class<?> getObjectType() {
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

    public GeneratorFactoryBean beanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public GeneratorFactoryBean clazz(String clazz) {
        this.clazz = clazz;
        return this;
    }
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public GeneratorConfiguration getGeneratorConfiguration() {
        return generatorConfiguration;
    }


    public GeneratorFactoryBean generatorConfiguration(GeneratorConfiguration generatorConfiguration) {
        this.generatorConfiguration = generatorConfiguration;
        return this;
    }

    public void setGeneratorConfiguration(GeneratorConfiguration generatorConfiguration) {
        this.generatorConfiguration = generatorConfiguration;
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            generator = ((Generator<Object, Object>)getClass().getClassLoader().loadClass(Optional.ofNullable(clazz).orElse(DEFAULT_GENERATOR)).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new BeanCreationException("bean创建失败",e);
        }
    }
}
