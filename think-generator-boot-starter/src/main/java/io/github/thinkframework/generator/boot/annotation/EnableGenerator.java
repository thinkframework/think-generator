package io.github.thinkframework.generator.boot.annotation;

import io.github.thinkframework.generator.boot.config.GeneratorAutoConfiguration;
import io.github.thinkframework.generator.config.GeneratorProperties;
import io.github.thinkframework.generator.config.GeneratorProperties.GeneratorConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(value = {GeneratorAutoConfiguration.class,})
@Documented
public @interface EnableGenerator {

}
