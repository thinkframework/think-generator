package org.think.swing.other;

import javax.swing.*;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorLogFrame extends JFrame {
    private PipedInputStream pipedInputStream;
    private PipedOutputStream pipedOutputStream;
    public void GeneratorLogFrame(){
        String TITLE = "日志";
        int WIDTH = 600;
        int HEIGHT = 480;
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);// 设置大小
        setLocationRelativeTo(null);//居中
        JTextArea jTextArea = new JTextArea();
        add(jTextArea);
        try {
            pipedInputStream = new PipedInputStream();
            pipedOutputStream = new PipedOutputStream();
            pipedOutputStream.connect(pipedInputStream);
            PrintStream printStream = new PrintStream(pipedOutputStream);
            System.setOut(printStream);
            System.setErr(printStream);
        }
        catch(IOException e) {
            System.exit(1);
        }
    }
}
