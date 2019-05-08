package io.github.thinkframework.boot.generator.annotation;

import io.github.thinkframework.generator.GeneratorFactoryBean;
import io.github.thinkframework.generator.provider.TableGeneratorProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class GeneratorConfigurationBean {
//    @Bean
//    @Conditional(WindowsCondition.class)
//    public Generator windows(){
//        return new Generator();
//    }
//
//
//    @Bean
//    @Conditional(MacOsCondition.class)
//    public Generator macos(){
//        return new Generator();
//    }
//
//
//    @Bean
//    @Conditional(LinuxCondition.class)
//    public Generator linux(){
//        return new Generator();
//    }


    @Bean
    @ConditionalOnProperty(value = "think.generator.configuration.template")
    public GeneratorFactoryBean generatorFactoryBean(){
        return new GeneratorFactoryBean();
    }

    @Bean
    @ConditionalOnProperty(value = "think.generator.configuration.template")
    public TableGeneratorProvider tableGeneratorProvider(){
        return new TableGeneratorProvider();
    }
}
