package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工厂模式
 */
public class GeneratorFactoryBean implements BeanFactoryAware, FactoryBean, InitializingBean{//, BeanFactoryPostProcessor {

    private BeanFactory beanFactory;

    //TODO 要不要使用依赖注入
    @Autowired
    private GeneratorConfiguration generatorConfiguration;

    private Generator generator;

    public GeneratorFactoryBean(){

    }

    @Override
    public Generator getObject() throws Exception {
        return generator;
    }

    @Override
    public Class<?> getObjectType() {
        return Generator.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        generator = new Generator()
            .beanFactory(beanFactory)
            .generatorConfiguration(generatorConfiguration);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
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


//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//
//    }
}
