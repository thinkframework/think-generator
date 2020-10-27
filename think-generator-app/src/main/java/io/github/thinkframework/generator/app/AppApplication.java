package io.github.thinkframework.generator.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author hdhxby
 * @since 2017/3/24
 */
@SpringBootApplication
//@ImportResource(value = {"classpath*:/applicationContext.xml"})
public class AppApplication {

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");//Mac OS X 菜单条
        new SpringApplicationBuilder(AppApplication.class)
        .headless(false)
        .run(args);
    }
}

