package org.think.swing;

import java.awt.*;
import java.io.IOException;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class Application {
    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GeneratorApplicationFrame generatorApplicationFrame = new GeneratorApplicationFrame();
                generatorApplicationFrame.setVisible(true);//窗口可见
            }
        });
    }
}
