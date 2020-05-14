package io.github.thinkframework.generator.boot.config;

import io.github.thinkframework.generator.GeneratorFactoryBean;
import io.github.thinkframework.generator.config.GeneratorProperties;
import io.github.thinkframework.generator.listener.GeneratorListener;
import io.github.thinkframework.generator.provider.ConfigurationGeneratorProvider;
import io.github.thinkframework.generator.provider.TableGeneratorProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {GeneratorProperties.class})
@ConditionalOnProperty(prefix = "think.generator",name = {"enabled"},havingValue = "true",matchIfMissing = true)
public class GeneratorAutoConfiguration{

    @Bean
    @ConditionalOnClass(value = {GeneratorFactoryBean.class})
    @ConditionalOnMissingBean(value = {GeneratorFactoryBean.class})
    public GeneratorFactoryBean generatorFactoryBean() {
        return new GeneratorFactoryBean();
    }

    @Bean
    @ConditionalOnBean(value = {GeneratorFactoryBean.class})
    @ConditionalOnMissingBean(value = {ConfigurationGeneratorProvider.class})
    public ConfigurationGeneratorProvider configurationGeneratorProvider() {
        return new ConfigurationGeneratorProvider();
    }

    @Bean
    @ConditionalOnBean(value = {GeneratorFactoryBean.class})
    @ConditionalOnMissingBean(value = {TableGeneratorProvider.class})
    public TableGeneratorProvider tableGeneratorProvider() {
        return new TableGeneratorProvider();
    }

    @Bean
    @ConditionalOnBean(value = {GeneratorFactoryBean.class})
    @ConditionalOnMissingBean(value = {GeneratorListener.class})
    public GeneratorListener generatorListener() {
        return new GeneratorListener();
    }
}
