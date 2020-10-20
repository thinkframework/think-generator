package io.github.thinkframework.generator.swing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author lixiaobin
 */
@SpringBootApplication
public class GeneratorApplication {

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(GeneratorApplication.class)
            .headless(false)
            .run(args);
    }
}
