package io.github.thinkframework.generator.boot;

import io.github.thinkframework.generator.beans.factory.GeneratorFactoryBean;
import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(value = GeneratorFactoryBean.class)
@ConditionalOnBean(GeneratorMarkerConfiguration.Marker.class)
@EnableConfigurationProperties(value = {GeneratorProperties.class})
public class GeneratorAutoConfiguration {

    @Autowired
    private GeneratorProperties generatorProperties;

    @Bean
    public GeneratorConfiguration generatorConfiguration(){
        return generatorProperties.getConfiguration();
    }

    @Bean
    public GeneratorFactoryBean generatorFactoryBean(){
        return new GeneratorFactoryBean(generatorConfiguration());
    }
}
