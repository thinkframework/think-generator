package io.github.thinkframework.boot.generator.config;

import io.github.thinkframework.generator.Generator;
import io.github.thinkframework.generator.annotation.GeneratorImportBeanDefinitionRegistrar;
import io.github.thinkframework.generator.config.GeneratorProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnBean(value = Generator.class)
@EnableConfigurationProperties(value = {GeneratorProperties.class})
@Import(value = {GeneratorImportBeanDefinitionRegistrar.class})
public class GeneratorAutoConfiguration {
}
