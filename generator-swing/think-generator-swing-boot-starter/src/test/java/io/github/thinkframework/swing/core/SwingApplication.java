package io.github.thinkframework.swing.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author hdhxby
 * @since 2017/3/24
 */
@SpringBootApplication
public class SwingApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SwingApplication.class)
        .headless(false)
        .run(args);
    }
}

