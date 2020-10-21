package io.github.thinkframework.generator.swing.plugin.component;

import io.github.thinkframework.generator.core.config.GeneratorProperties;
import io.github.thinkframework.generator.swing.core.util.GeneratorXmlUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author lixiaobin
 */
public class GeneratorConfigurationPanel extends JPanel implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private GeneratorProperties generatorProperties;

	private GeneratorXmlUtil applicationContextGeneratorXmlUtil = new GeneratorXmlUtil();
	private JTextField frameField;
	private JTextField companyNameField;
	private JTextField appField;
	private JTextField moduleField;
	private JTextField namespaceField;
    private JTextArea extensionsTextArea;
    private JTextArea convertsTextArea;
    private JTextArea prefixsTextArea;
    private JTextArea ignoresTextArea;

	public JPanel centerNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0,200));
		northPanel.setLayout(new GridLayout(5,2));
		Border border = BorderFactory.createTitledBorder("配置");
		northPanel.setBorder(border);

		JLabel frameNameLabel = new JLabel("frameName");
		northPanel.add(frameNameLabel);

		frameField = new JTextField();
		frameField.setColumns(10);
		northPanel.add(frameField);

		JLabel companyNameLabel = new JLabel("companyName");
		northPanel.add(companyNameLabel);

		companyNameField = new JTextField();
		companyNameField.setColumns(10);
		northPanel.add(companyNameField);

		JLabel urlLabel = new JLabel("appName");
		northPanel.add(urlLabel);

		appField = new JTextField();
		appField.setColumns(10);
		northPanel.add(appField);

        JLabel namespaceLabel = new JLabel("moduleName");
		northPanel.add(namespaceLabel);

		moduleField = new JTextField();
		moduleField.setColumns(10);
		northPanel.add(moduleField);

		JLabel passwordLabel = new JLabel("namespace");
		northPanel.add(passwordLabel);

		namespaceField = new JTextField();
		northPanel.add(namespaceField);

		return northPanel;
	}

	public JScrollPane centerCenterPanel(){
		JScrollPane centerPanel = new JScrollPane();
		Border propertiesBorder = BorderFactory.createTitledBorder("connectProperties");
        extensionsTextArea = new JTextArea();
        extensionsTextArea.setRows(10);
		centerPanel.setViewportView(extensionsTextArea);
		centerPanel.setBorder(propertiesBorder);
		return centerPanel;
	}

	public JPanel centerSouthPanel(){
		JPanel southPanel = new JPanel();

		southPanel.add(new JButton(new AbstractAction() {
			private static final long serialVersionUID = 1L;
			{
				putValue(Action.NAME, "取消");
			}
			public void actionPerformed(ActionEvent e) {
				GeneratorConfigurationPanel.this.setVisible(false);
			}
		}));

		southPanel.add(new JButton(new AbstractAction() {
			private static final long serialVersionUID = 1L;
			{
				putValue(Action.NAME, "确定");
			}
			public void actionPerformed(ActionEvent e) {
				GeneratorXmlUtil.GeneratorDataSourceConfiguration generatorDataSourceConfiguration = new GeneratorXmlUtil.GeneratorDataSourceConfiguration();
				generatorDataSourceConfiguration.setId(frameField.getText());
				generatorDataSourceConfiguration.setDriverClassName(companyNameField.getText());
				generatorDataSourceConfiguration.setUrl(appField.getText());
				generatorDataSourceConfiguration.setUsername(moduleField.getText());
                generatorDataSourceConfiguration.setPassword(namespaceField.getText());
				applicationContextGeneratorXmlUtil.setDataSourceBean(generatorDataSourceConfiguration);

			}
		}));

		return southPanel;
	}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
	    generatorProperties = applicationContext.getBean(GeneratorProperties.class);
        setLayout(new BorderLayout());
        add(centerNorthPanel(),BorderLayout.NORTH);
        add(centerCenterPanel(),BorderLayout.CENTER);
        add(centerSouthPanel(), BorderLayout.SOUTH);
    }
}
