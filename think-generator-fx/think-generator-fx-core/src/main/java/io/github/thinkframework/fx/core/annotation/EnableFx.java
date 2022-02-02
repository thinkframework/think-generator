package io.github.thinkframework.fx.core.annotation;

import io.github.thinkframework.fx.core.configuration.FXConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(value = {FXConfiguration.class})
@Documented
public @interface EnableFx {
}
