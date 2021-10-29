package io.github.thinkframework.generator.boot;

import io.github.thinkframework.generator.beans.factory.GeneratorFactoryBean;
import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import io.github.thinkframework.generator.core.chain.*;
import io.github.thinkframework.generator.core.command.FreeMarkerCommand;
import io.github.thinkframework.generator.core.command.GeneratorCommand;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
        GeneratorFactoryBean generatorFactoryBean =  new GeneratorFactoryBean(generatorConfiguration());
        generatorFactoryBean.responsibilities(List.of(configurationGeneratorResponsibility(),
                idGeneratorResponsibility(),
                tableGeneratorResponsibility()))
                .command(freeMarkerCommand());
        return generatorFactoryBean;
    }

    public GeneratorResponsibility configurationGeneratorResponsibility(){
        return new ConfigurationResponsibility();
    }

    public GeneratorResponsibility idGeneratorResponsibility(){
        return new IDResponsibility();
    }

    public GeneratorResponsibility tableGeneratorResponsibility(){
        return new TableResponsibility();
    }

    public GeneratorCommand freeMarkerCommand(){
        return new FreeMarkerCommand();
    }
}
