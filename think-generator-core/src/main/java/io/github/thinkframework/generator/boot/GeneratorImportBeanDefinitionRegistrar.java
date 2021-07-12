package io.github.thinkframework.generator.boot;

import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import io.github.thinkframework.generator.core.GeneratorFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Random;
import java.util.stream.Stream;

public class GeneratorImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    private static final Logger log = LoggerFactory.getLogger(GeneratorImportBeanDefinitionRegistrar.class);


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String[] names = ((DefaultListableBeanFactory) registry).getBeanNamesForType(GeneratorProperties.class);
        Stream.of(names).forEach(name -> {
        log.debug("加载 BeanDefinition: {}", name);
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
            builder.getBeanDefinition().setBeanClass(GeneratorFactoryBean.class);
            builder.addPropertyReference("properties",name);
            registry.registerBeanDefinition(name+ new Random().nextInt(), builder.getBeanDefinition());
        });
    }
}
