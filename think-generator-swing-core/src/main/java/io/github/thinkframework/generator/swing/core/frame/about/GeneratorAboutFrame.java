package io.github.thinkframework.generator.swing.core.frame.about;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 帮助窗口
 * @author hdhxby
 * @email hdhxby@qq.com
 */
public class GeneratorAboutFrame extends JFrame implements ResourceLoaderAware, InitializingBean {

    private ResourceLoader resourceLoader;

    @Override
    public void afterPropertiesSet() throws Exception {
        setTitle("关于");
        setSize(480,320);
        setLocationRelativeTo(null);//居中
        add(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics2D = (Graphics2D)g;
                Image image = null;
                try {
                    image = new ImageIcon(resourceLoader.getResource("icon.png").getURL()).getImage();
                    graphics2D.drawImage(image,10,10,null);
                    graphics2D.drawString("代码生成工具",300,50);
                } catch (IOException e) {
                }
            }

            public Dimension getPreferredSize() {
                return new Dimension(480,320);
            }
        });
        pack();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
