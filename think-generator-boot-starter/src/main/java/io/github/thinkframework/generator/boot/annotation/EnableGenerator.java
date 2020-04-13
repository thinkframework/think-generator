package io.github.thinkframework.generator.boot.annotation;

import io.github.thinkframework.generator.boot.config.GeneratorAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(GeneratorAutoConfiguration.class)
@Documented
public @interface EnableGenerator {
}
