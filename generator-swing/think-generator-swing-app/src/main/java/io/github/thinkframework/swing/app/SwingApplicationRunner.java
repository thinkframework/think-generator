package io.github.thinkframework.swing.app;

import io.github.thinkframework.swing.core.component.frame.GeneratorMainFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class SwingApplicationRunner implements ApplicationRunner {

    @Autowired
    private GeneratorMainFrame generatorMainFrame;

    @Override
    public void run(ApplicationArguments args) {
        EventQueue.invokeLater(() -> generatorMainFrame.setVisible(true));//窗口可见
    }
}
