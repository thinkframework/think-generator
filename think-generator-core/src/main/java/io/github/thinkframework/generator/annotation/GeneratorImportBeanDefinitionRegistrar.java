package io.github.thinkframework.generator.annotation;

import io.github.thinkframework.generator.GeneratorFactoryBean;
import io.github.thinkframework.generator.config.GeneratorProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Random;
import java.util.stream.Stream;

@Slf4j
public class GeneratorImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


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
