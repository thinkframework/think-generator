package io.github.thinkframework.swing.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

/**
 * @author hdhxby
 * @since 2017/3/24
 */
@SpringBootApplication
@ImportResource(value = {"classpath*:/applicationContext.xml"})
public class SwingApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SwingApplication.class)
        .headless(false)
        .run(args);
    }
}

