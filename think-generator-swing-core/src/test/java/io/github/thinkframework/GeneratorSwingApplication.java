package io.github.thinkframework;


import io.github.thinkframework.generator.swing.core.annotation.EnableSwing;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author hdhxby
 * @since 2017/3/24
 */
@EnableSwing
@SpringBootApplication
public class GeneratorSwingApplication {

    public static void main(String[] args) {
        if("Mac OS X".equals(System.getProperty("os.name"))) {//Mac OS X 菜单条
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }

        new SpringApplicationBuilder(GeneratorSwingApplication.class)
        .headless(false)
        .run(args);
    }
}

