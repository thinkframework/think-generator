package io.github.thinkframework.generator.swing;

import io.github.thinkframework.generator.swing.frame.control.GeneratorControlFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class SwingApplicationRunner implements ApplicationRunner {

    @Autowired
    private GeneratorControlFrame generatorControlFrame;

    @Override
    public void run(ApplicationArguments args) {
        EventQueue.invokeLater(() -> generatorControlFrame.setVisible(true));//窗口可见
    }
}
