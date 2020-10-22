package io.github.thinkframework.generator.swing.plugin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author lixiaobin
 */
@SpringBootApplication
public class GeneratorPluginApplication {

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(GeneratorPluginApplication.class)
            .headless(false)
            .run(args);
    }
}
