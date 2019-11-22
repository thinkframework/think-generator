package io.github.thinkframework.jms;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class JmsApplication  {

    public static void main(String[] args) {
            new SpringApplicationBuilder(JmsApplication.class)
                .headless(false)
                .run(args);
    }
}
