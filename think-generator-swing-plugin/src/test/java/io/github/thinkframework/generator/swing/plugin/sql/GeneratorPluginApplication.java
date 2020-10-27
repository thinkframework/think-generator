package io.github.thinkframework.generator.swing.plugin.sql;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author hdhxby
 */
@SpringBootApplication
public class GeneratorPluginApplication {

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(GeneratorPluginApplication.class)
            .headless(false)
            .run(args);
    }
}
