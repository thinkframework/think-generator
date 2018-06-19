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
public class GeneratorLogFrame extends JInternalFrame {
    private PipedInputStream pipedInputStream;
    private PipedOutputStream pipedOutputStream;
    public void GeneratorLogFrame(){
        setSize(800,600);
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
