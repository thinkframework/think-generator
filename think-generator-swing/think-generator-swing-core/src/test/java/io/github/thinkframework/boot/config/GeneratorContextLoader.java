package io.github.thinkframework.boot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootContextLoader;

public class GeneratorContextLoader extends SpringBootContextLoader {
    @Override
    protected SpringApplication getSpringApplication() {
        SpringApplication application = super.getSpringApplication();
        application.setHeadless(false);
        return application;
    }
}
