package io.github.thinkframework.swing;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
@SpringBootApplication
@ImportResource(value = {"classpath*://applicationContext.xml"})
public class SwingMain {

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");//Mac OS X 菜单条
        new SpringApplicationBuilder(SwingMain.class)
        .headless(false)
        .run(args);
    }
}

