package io.github.thinkframework.generator.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(GeneratorConfigurationBean.class)
@Documented
public @interface EnableGenerator {
}
