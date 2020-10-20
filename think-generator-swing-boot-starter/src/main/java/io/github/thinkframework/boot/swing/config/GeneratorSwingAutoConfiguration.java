package io.github.thinkframework.boot.swing.config;

import io.github.thinkframework.generator.swing.configuration.SwingConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {SwingConfiguration.class})
public class GeneratorSwingAutoConfiguration {
}
