package io.github.thinkframework.generator.boot.generator.config;

import io.github.thinkframework.generator.GeneratorFactoryBean;
import io.github.thinkframework.generator.config.GeneratorConfiguration;
import io.github.thinkframework.generator.listener.GeneratorListener;
import io.github.thinkframework.generator.provider.TableGeneratorProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties({GeneratorConfiguration.class})
@Import({GeneratorConfigurationBean.class, GeneratorImportSelector.class, GeneratorImportBeanDefinitionRegistrar.class})
public class GeneratorAutoConfiguration {

    @Bean
    @ConditionalOnBean(value = {GeneratorConfiguration.class})
    @ConditionalOnMissingBean(value = {GeneratorFactoryBean.class})
    public GeneratorFactoryBean generatorFactoryBean(GeneratorConfiguration generatorConfiguration) {
        return new GeneratorFactoryBean()
            .generatorConfiguration(generatorConfiguration);
    }

    @Bean
    @ConditionalOnBean(value = {GeneratorConfiguration.class})
    @ConditionalOnMissingBean(value = {GeneratorListener.class})
    public GeneratorListener generatorListener() {
        return new GeneratorListener();
    }

    @Bean
    @ConditionalOnBean(value = {GeneratorConfiguration.class})
    @ConditionalOnMissingBean(value = {TableGeneratorProvider.class})
    public TableGeneratorProvider tableGeneratorProvider() {
        return new TableGeneratorProvider();
    }

}
