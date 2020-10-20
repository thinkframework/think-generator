package io.github.thinkframework;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
@SpringBootApplication
@ImportResource(value = {"classpath:applicationContext.xml"})
public class SwingApplication {

    public static void main(String[] args) {
        if("Mac OS X".equals(System.getProperty("os.name"))) {//Mac OS X 菜单条
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }

        new SpringApplicationBuilder(SwingApplication.class)
        .headless(false)
        .run(args);
    }
}

