package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorConfiguration;
import io.github.thinkframework.generator.provider.GeneratorProvider;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.stream.Collectors;

/**
 * 工厂模式
 */
public class GeneratorFactoryBean implements BeanFactoryAware, BeanNameAware, FactoryBean, BeanFactoryPostProcessor {

    private BeanFactory beanFactory;

    private String name;

    private GeneratorConfiguration generatorConfiguration;

    private Generator generator;

    public GeneratorFactoryBean() {

    }

    @Override
    public Generator getObject() throws Exception {
        return generator
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
        generator = new Generator()
            .generatorConfiguration(generatorConfiguration);
    }
}
