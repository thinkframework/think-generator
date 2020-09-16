package io.github.thinkframework.boot.generator.config;

import io.github.thinkframework.generator.annotation.GeneratorImportBeanDefinitionRegistrar;
import io.github.thinkframework.generator.config.GeneratorProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(value = {GeneratorProperties.class})
@Import(value = {GeneratorProperties.class, GeneratorImportBeanDefinitionRegistrar.class,})
@ConditionalOnProperty(prefix = "think.generator",name = {"enabled"},havingValue = "true",matchIfMissing = true)
public class GeneratorAutoConfiguration {
}
