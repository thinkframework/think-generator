package io.github.thinkframework.swing.core.annotation;

import io.github.thinkframework.swing.core.configuration.SwingConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(value = {SwingConfiguration.class})
@Documented
public @interface EnableSwing {
}
