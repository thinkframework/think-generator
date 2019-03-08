package io.github.thinkframework.swing;

import io.github.thinkframework.swing.control.GeneratorControlFrame;

import java.awt.*;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class SwingApplication {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->{
            new GeneratorControlFrame()
                .setVisible(true);//窗口可见
        });
    }
}
