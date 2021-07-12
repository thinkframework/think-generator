package io.github.thinkframework.generator.boot;

import io.github.thinkframework.generator.boot.context.properties.GeneratorProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用Generator
 * @author hdhxby
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableConfigurationProperties(value = {GeneratorProperties.class})
@Import(value = {GeneratorImportBeanDefinitionRegistrar.class})
@Documented
public @interface EnableGenerator {

}
