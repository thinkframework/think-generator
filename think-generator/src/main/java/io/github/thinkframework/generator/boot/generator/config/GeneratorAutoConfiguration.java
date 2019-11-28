package io.github.thinkframework.generator.boot.generator.config;

import io.github.thinkframework.generator.GeneratorFactoryBean;
import io.github.thinkframework.generator.config.GeneratorConfiguration;
import io.github.thinkframework.generator.provider.PropertiesGeneratorProvider;
import io.github.thinkframework.generator.provider.TableGeneratorProvider;
import io.github.thinkframework.generator.listener.GeneratorListener;
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
    @ConditionalOnMissingBean(value = {GeneratorFactoryBean.class})
    public GeneratorFactoryBean generatorFactoryBean(){
        return new GeneratorFactoryBean();
    }

    @Bean
    @ConditionalOnMissingBean(value = {GeneratorListener.class})
    public GeneratorListener generatorListener(){
        return new GeneratorListener();
    }

    @Bean
    @ConditionalOnBean(value = {GeneratorFactoryBean.class})
    @ConditionalOnMissingBean(value = {PropertiesGeneratorProvider.class})
    public PropertiesGeneratorProvider propertiesGeneratorProvider(){
        return new PropertiesGeneratorProvider();
    }

    @Bean
    @ConditionalOnBean(value = {GeneratorFactoryBean.class})
    @ConditionalOnMissingBean(value = {TableGeneratorProvider.class})
    public TableGeneratorProvider tableGeneratorProvider(){
        return new TableGeneratorProvider();
    }

}
