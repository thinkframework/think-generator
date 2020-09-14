package io.github.thinkframework.generator.annotation;

import io.github.thinkframework.generator.config.GeneratorProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用Generator
 * @author hdhxby
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(value = {GeneratorProperties.class,GeneratorImportBeanDefinitionRegistrar.class,})
@Documented
public @interface EnableGenerator {

}
