package org.think.swing;

import java.awt.*;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class Application {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->{
            GeneratorApplicationFrame generatorApplicationFrame = new GeneratorApplicationFrame();
            generatorApplicationFrame.setVisible(true);//窗口可见
        });
    }
}
