package io.github.thinkframework.boot.generator.config;

import io.github.thinkframework.generator.core.Generator;
import io.github.thinkframework.generator.core.annotation.GeneratorImportBeanDefinitionRegistrar;
import io.github.thinkframework.generator.core.config.GeneratorProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnMissingBean(value = Generator.class)
@EnableConfigurationProperties(value = {GeneratorProperties.class})
@Import(value = {GeneratorImportBeanDefinitionRegistrar.class})
public class GeneratorAutoConfiguration {
}
