package io.github.thinkframework.generator.app;

import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author hdhxby
 * @since 2017/3/24
 */
public class AppMain {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AppApplication.class)
        .headless(false)
        .run(args);
    }
}

