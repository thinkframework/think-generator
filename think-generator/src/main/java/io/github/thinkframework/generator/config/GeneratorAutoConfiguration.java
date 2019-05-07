package io.github.thinkframework.generator.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Conditional
@EnableConfigurationProperties({GeneratorConfiguration.class})
//@Import({GeneratorConfigurationBean.class})
public class GeneratorAutoConfiguration {

}
