package io.github.thinkframework.boot.generator.annotation;

import io.github.thinkframework.boot.generator.config.GeneratorAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(GeneratorAutoConfiguration.class)
@Documented
public @interface EnableGenerator {
}
