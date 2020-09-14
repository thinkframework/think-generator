package io.github.thinkframework.generator;

import io.github.thinkframework.generator.config.GeneratorProperties;
import io.github.thinkframework.generator.provider.GeneratorProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 工厂模式
 */
@Slf4j
public class GeneratorFactoryBean implements BeanFactoryAware, BeanNameAware, FactoryBean<GeneratorStrategy> {

    private String[] providers = new String[]{
        "io.github.thinkframework.generator.provider.GeneratorProvider",
        "io.github.thinkframework.generator.provider.TableGeneratorProvider"
    };
    public static final String DEFAULT_GENERATOR = "io.github.thinkframework.generator.GeneratorTable";

    private BeanFactory beanFactory;

    private String name;

    private String clazz = DEFAULT_GENERATOR;

    private GeneratorStrategy generator;

    private List<GeneratorProvider> generatorProvider(){
        return Stream.of(providers).map(provider -> {
            try {
                return (GeneratorProvider)Class.forName(provider).newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                return null;
            }
        }).collect(Collectors.toList());
    }
    @Override
    public GeneratorStrategy getObject() throws Exception {
        generator = ((GeneratorStrategy)Class.forName(clazz).newInstance())
            .generatorConfiguration(beanFactory.getBean(GeneratorProperties.class).getConfiguration())
            .generatorProviders(generatorProvider());
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

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
