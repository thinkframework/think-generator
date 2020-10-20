package io.github.thinkframework.generator.swing.frame.config;

import io.github.thinkframework.generator.swing.comp.list.GeneratorList;
import org.springframework.beans.factory.InitializingBean;

import javax.swing.*;

/**
 *
 * @author lixiaobin
 */
public class GeneratorConfigureFrame extends JFrame implements InitializingBean {

	private GeneratorList generatorList;

    private GeneratorConfigurePanel generatorConfigurePanel;

    @Override
    public void afterPropertiesSet() {
        String TITLE = "新建/选择数据库连接";
        int WIDTH = 640;
        int HEIGHT = 480;
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);// 设置大小
        setLocationRelativeTo(null);//居中
        JSplitPane jSplitPane = new JSplitPane();
        jSplitPane.setLeftComponent(new JScrollPane(generatorList));
        jSplitPane.setRightComponent(new JScrollPane(generatorConfigurePanel));

        jSplitPane.setOneTouchExpandable(true);
        jSplitPane.setContinuousLayout(true);

        add(jSplitPane);
    }

    public GeneratorList getGeneratorList() {
        return generatorList;
    }

    public void setGeneratorList(GeneratorList generatorList) {
        this.generatorList = generatorList;
    }

    public GeneratorConfigurePanel getGeneratorConfigurePanel() {
        return generatorConfigurePanel;
    }

    public void setGeneratorConfigurePanel(GeneratorConfigurePanel generatorConfigurePanel) {
        this.generatorConfigurePanel = generatorConfigurePanel;
    }
}
