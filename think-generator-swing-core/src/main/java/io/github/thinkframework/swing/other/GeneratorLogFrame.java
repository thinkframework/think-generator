package io.github.thinkframework.swing.other;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.OutputStreamAppender;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorLogFrame extends JPanel implements InitializingBean {

    private JTextArea jTextArea = new JTextArea();
    private StringBuffer stringBuffer = new StringBuffer();
    @Override
    public void afterPropertiesSet() throws Exception {
        setLayout(new BorderLayout());
        add(jTextArea);

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger =loggerContext.getLogger("ROOT");
        Appender appender = new OutputStreamAppender(){
            @Override
            public void start() {
                this.setOutputStream(new OutputStream() {
                    public void write(int b) throws IOException {
//                        stringBuffer.append(b);
                    }

                    public void write(byte[] b) throws IOException {
//                        stringBuffer.append(new String(b));
                    }

                    public void write(byte[] b, int off, int len) throws IOException {
//                        stringBuffer.append(new String(b, off, len));
                    }

                    public void flush() throws IOException {
//                        EventQueue.invokeLater(() -> {
//                            jTextArea.setText(stringBuffer.toString());
//                        });
                    }
                });
                super.start();
            }
        };
        appender.setContext(loggerContext);
        appender.setName("SWING");
        appender.start();
        logger.addAppender(appender);
    }
}
