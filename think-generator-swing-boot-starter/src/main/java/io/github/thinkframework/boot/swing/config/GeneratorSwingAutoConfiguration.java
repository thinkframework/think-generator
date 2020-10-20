package io.github.thinkframework.boot.swing.config;

import io.github.thinkframework.swing.SwingConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {SwingConfiguration.class})
public class GeneratorSwingAutoConfiguration {
}
