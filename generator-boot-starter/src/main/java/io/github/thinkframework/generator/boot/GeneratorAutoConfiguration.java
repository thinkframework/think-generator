package io.github.thinkframework.generator.boot;

import io.github.thinkframework.generator.beans.factory.GeneratorTableFactoryBean;
import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import io.github.thinkframework.generator.core.chain.*;
import io.github.thinkframework.generator.core.command.FreeMarkerFileCommand;
import io.github.thinkframework.generator.core.command.GeneratorCommand;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.table.chain.TableResponsibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnMissingBean(value = GeneratorTableFactoryBean.class)
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
    public GeneratorTableFactoryBean generatorFactoryBean(){
        GeneratorTableFactoryBean generatorTableFactoryBean =  new GeneratorTableFactoryBean(generatorConfiguration());
        generatorTableFactoryBean.responsibilities(List.of(configurationGeneratorResponsibility(),
                idGeneratorResponsibility(),
                tableGeneratorResponsibility()))
                .command(freeMarkerCommand());
        return generatorTableFactoryBean;
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
        return new FreeMarkerFileCommand();
    }
}
