package io.github.thinkframework.swing;

import io.github.thinkframework.boot.swing.SwingConfiguration;
import io.github.thinkframework.swing.control.GeneratorControlFrame;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import java.awt.*;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
@SpringBootApplication
@Import({SwingConfiguration.class})
public class SwingApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
            new SpringApplicationBuilder(SwingApplication.class)
            .headless(false)
                .run(args);

        EventQueue.invokeLater(() ->{
            applicationContext.getBean(GeneratorControlFrame.class).setVisible(true);//窗口可见
        });
    }
}
