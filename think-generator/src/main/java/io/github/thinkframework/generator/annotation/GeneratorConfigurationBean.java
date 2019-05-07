package io.github.thinkframework.generator.annotation;

import io.github.thinkframework.generator.Generator;
import io.github.thinkframework.generator.GeneratorFactoryBean;
import io.github.thinkframework.generator.condition.LinuxCondition;
import io.github.thinkframework.generator.condition.MacOsCondition;
import io.github.thinkframework.generator.condition.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class GeneratorConfigurationBean {
    @Bean
    @Conditional(WindowsCondition.class)
    public Generator windows(){
        return new Generator();
    }


    @Bean
    @Conditional(MacOsCondition.class)
    public Generator macos(){
        return new Generator();
    }


    @Bean
    @Conditional(LinuxCondition.class)
    public Generator linux(){
        return new Generator();
    }

    @Bean
//    @ConditionalOnProperty(name = "think",havingValue = "generator")
//    @ConditionalOnExpression("${think} != null")
    public GeneratorFactoryBean generatorFactoryBean(){
        return new GeneratorFactoryBean();
    }
}
