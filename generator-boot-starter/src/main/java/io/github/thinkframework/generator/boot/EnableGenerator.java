package io.github.thinkframework.generator.boot;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用Generator
 * @author hdhxby
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GeneratorMarkerConfiguration.class)
public @interface EnableGenerator {

}
