package io.github.thinkframework.generator.swing.plugin.sql.frame;

import org.springframework.beans.factory.InitializingBean;

import javax.swing.*;

/**
 *
 * @author hdhxby
 */
public class GeneratorConfigurationFrame extends JFrame implements InitializingBean {

    private GeneratorConfigurationPanel generatorConfigurationPanel;

    @Override
    public void afterPropertiesSet() {
        String TITLE = "配置";
        int WIDTH = 640;
        int HEIGHT = 480;
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);// 设置大小
        setLocationRelativeTo(null);//居中
        add(generatorConfigurationPanel);
    }

    public GeneratorConfigurationPanel getGeneratorConfigurePanel() {
        return generatorConfigurationPanel;
    }

    public void setGeneratorConfigurePanel(GeneratorConfigurationPanel generatorConfigurationPanel) {
        this.generatorConfigurationPanel = generatorConfigurationPanel;
    }
}
