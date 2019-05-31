package io.github.thinkframework.boot.generator.config;

import io.github.thinkframework.generator.GeneratorFactoryBean;
import io.github.thinkframework.generator.config.GeneratorConfiguration;
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
    @ConditionalOnMissingBean(value = {GeneratorFactoryBean.class})
    public GeneratorFactoryBean generatorFactoryBean(){
        return new GeneratorFactoryBean();
    }

    @Bean
    @ConditionalOnBean(value = {GeneratorFactoryBean.class})
    @ConditionalOnMissingBean(value = {TableGeneratorProvider.class})
    public TableGeneratorProvider tableGeneratorProvider(){
        return new TableGeneratorProvider();
    }
}
