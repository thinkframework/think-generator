package io.github.thinkframework.boot.generator.config;

import io.github.thinkframework.generator.core.GeneratorFactoryBean;
import io.github.thinkframework.generator.boot.GeneratorImportBeanDefinitionRegistrar;
import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnMissingBean(value = GeneratorFactoryBean.class)
@EnableConfigurationProperties(value = {GeneratorProperties.class})
@Import(value = {GeneratorImportBeanDefinitionRegistrar.class})
public class GeneratorAutoConfiguration {
}
