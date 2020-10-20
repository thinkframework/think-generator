package io.github.thinkframework.swing.annotation;

import io.github.thinkframework.swing.SwingConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(value = {SwingConfiguration.class})
@Documented
public @interface EnableSwing {
}
